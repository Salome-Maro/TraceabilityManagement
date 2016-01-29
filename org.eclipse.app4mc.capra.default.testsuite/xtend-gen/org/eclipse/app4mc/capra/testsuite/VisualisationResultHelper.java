package org.eclipse.app4mc.capra.testsuite;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class VisualisationResultHelper {
  public final static String expectedMatrix1 = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("salt");
      _builder.newLine();
      _builder.append("{#");
      _builder.newLine();
      _builder.append(".|A1 : EClass|A2 : EClass|B : EClass");
      _builder.newLine();
      _builder.append("A1 : EClass |.|X|.");
      _builder.newLine();
      _builder.append("A2 : EClass |X|.|X");
      _builder.newLine();
      _builder.append("B : EClass |.|X|.");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  public final static String expectedMatrix2 = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("salt");
      _builder.newLine();
      _builder.append("{#");
      _builder.newLine();
      _builder.append(".|A2 : EClass");
      _builder.newLine();
      _builder.append("A1 : EClass |X");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  public final static String directDiagram = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("object \"A1 : EClass\" as o0 #pink");
      _builder.newLine();
      _builder.append("object \"A2 : EClass\" as o1");
      _builder.newLine();
      _builder.append("o0--o1:EObjectToEObject");
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
  
  public final static String transitiveDiagram = new Function0<String>() {
    public String apply() {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("object \"A1 : EClass\" as o0 #pink");
      _builder.newLine();
      _builder.append("object \"A2 : EClass\" as o1");
      _builder.newLine();
      _builder.append("object \"B : EClass\" as o2");
      _builder.newLine();
      _builder.append("o0--o1:EObjectToEObject");
      _builder.newLine();
      _builder.append("o1--o2:EObjectToEObject");
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      return _builder.toString();
    }
  }.apply();
}
