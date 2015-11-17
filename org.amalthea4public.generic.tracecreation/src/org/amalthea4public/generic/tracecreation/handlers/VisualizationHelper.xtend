package org.amalthea4public.generic.tracecreation.handlers

import java.util.Collection
import java.util.List
import java.util.Optional
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper
import org.eclipse.emf.ecore.EObject

class VisualizationHelper {
	def static String createMatrix(Optional<EObject> traceModel, Collection<EObject> firstElements, Collection<EObject> secondElements){
	val traceAdapter = TraceCreationHelper.getTraceMetamodelAdapter().get()
	'''
	@startuml
	salt
	{#
	«IF firstElements != null»
	.«FOR e : secondElements»|«TraceCreationHelper.getIdentifier(e)»«ENDFOR»
	«FOR first : firstElements»
	«TraceCreationHelper.getIdentifier(first)» «FOR second : secondElements»|«IF traceAdapter.isThereATraceBetween(first, second, traceModel)»X«ELSE».«ENDIF»«ENDFOR»
	«ENDFOR»
	«ELSE»
	Choose two containers to show a traceability matrix of their contents.
	«ENDIF»
	}
	@enduml
	'''
	}
	
	def static String createNeighboursView(List<EObject> connectedElements, List<String> traceLabels, EObject selectedElement){
	var i = 1
	var j = 1
	'''
	@startuml
	object "«TraceCreationHelper.getIdentifier(selectedElement)»" as o0 #pink
	«FOR e:connectedElements»object "«TraceCreationHelper.getIdentifier(e)»" as o«i++»
	«ENDFOR»
	«FOR t:traceLabels» o0 --> o«j++» : "«t»"
	«ENDFOR» 
	@enduml
	'''
	}
}


 