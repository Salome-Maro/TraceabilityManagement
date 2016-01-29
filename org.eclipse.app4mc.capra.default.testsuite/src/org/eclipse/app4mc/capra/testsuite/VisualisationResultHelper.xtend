package org.eclipse.app4mc.capra.testsuite

class VisualisationResultHelper {
	public static val expectedMatrix1 = '''
	@startuml
	salt
	{#
	.|A1 : EClass|A2 : EClass|B : EClass
	A1 : EClass |.|X|.
	A2 : EClass |X|.|X
	B : EClass |.|X|.
	}
	
	@enduml
	'''
	 
	public static val expectedMatrix2 = '''
	@startuml
	salt
	{#
	.|A2 : EClass
	A1 : EClass |X
	}
	
	@enduml
	'''
	
	public static val directDiagram = '''
	@startuml
	object "A1 : EClass" as o0 #pink
	object "A2 : EClass" as o1
	o0--o1:EObjectToEObject
	@enduml
	'''
	
	public static val transitiveDiagram = '''
	@startuml
	object "A1 : EClass" as o0 #pink
	object "A2 : EClass" as o1
	object "B : EClass" as o2
	o0--o1:EObjectToEObject
	o1--o2:EObjectToEObject
	@enduml
	'''	
}
