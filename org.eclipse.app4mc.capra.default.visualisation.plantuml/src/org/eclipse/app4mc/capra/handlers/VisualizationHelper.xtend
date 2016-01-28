package org.eclipse.app4mc.capra.handlers

import java.util.Collection
import java.util.List
import java.util.Optional
import org.eclipse.app4mc.capra.generic.adapters.Connection
import org.eclipse.app4mc.capra.generic.helpers.EMFHelper
import org.eclipse.app4mc.capra.generic.helpers.ExtensionPointHelper
import org.eclipse.emf.ecore.EObject

class VisualizationHelper {
	def static String createMatrix(Optional<EObject> traceModel, Collection<EObject> firstElements, Collection<EObject> secondElements){
	val traceAdapter = ExtensionPointHelper.getTraceMetamodelAdapter().get()
	'''
	@startuml
	salt
	{#
	«IF firstElements != null»
	.«FOR e : secondElements»|«EMFHelper.getIdentifier(e)»«ENDFOR»
	«FOR first : firstElements»
	«EMFHelper.getIdentifier(first)» «FOR second : secondElements»|«IF traceAdapter.isThereATraceBetween(first, second, traceModel)»X«ELSE».«ENDIF»«ENDFOR»
	«ENDFOR»
	«ELSE»
	Choose two containers to show a traceability matrix of their contents.
	«ENDIF»
	}
	
	@enduml
	'''
	} 
	
	def static String createNeighboursView(List<Connection> connections, EObject selectedObject){
	var helper = new Connections(connections, selectedObject);
	'''
	@startuml
	object "«helper.originLabel()»" as «helper.originId()» #pink
	«FOR id:helper.objectIdsWithoutOrigin()»
	object "«helper.label(id)»" as «id»
	«ENDFOR»
	«FOR a:helper.arrows()» 
	«a»
	«ENDFOR» 
	@enduml
	''' 
	}
}
  

 