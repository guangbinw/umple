/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.963 modeling language!*/

package cruise.tracer.test;

public class PersonA
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PersonA Attributes
  private String name;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PersonA(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}

}