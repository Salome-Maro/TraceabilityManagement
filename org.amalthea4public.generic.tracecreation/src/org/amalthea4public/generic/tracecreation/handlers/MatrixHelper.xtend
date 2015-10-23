package org.amalthea4public.generic.tracecreation.handlers

import java.util.Optional
import org.eclipse.emf.ecore.EObject
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper
import java.util.Collection

class MatrixHelper {
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
}



