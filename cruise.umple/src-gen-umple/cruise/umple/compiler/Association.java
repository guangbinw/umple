/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package cruise.umple.compiler;
import java.util.*;

/**
 * Represents an association, typically between two Umple classes (1-1, 1-*, *-1, etc).
 * An association in UML/Umple represents a relationship that exists at run time
 * where instances of one class reference the other, and (if bidirectional) instance
 * of the other class reference the first cass.
 * The number of references is governed by the Multiplicity at each AssociationEnd
 * 
 * @umplesource Umple.ump 520
 * @umplesource Umple_Code.ump 1235
 */
// line 520 "../../../../src/Umple.ump"
// line 1235 "../../../../src/Umple_Code.ump"
public class Association
{
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Association Attributes
  private String name;
  private boolean isLeftNavigable;
  private boolean isRightNavigable;
  private List<Coordinate> positions;
  private Position tokenPosition;
  private Position tokenEndPosition;
  private boolean immutable;

  //Association Associations
  private List<AssociationEnd> ends;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  @umplesourcefile(line={535},file={"Umple.ump"},javaline={61},length={1})
  public Association(boolean aIsLeftNavigable, boolean aIsRightNavigable, AssociationEnd... allEnds)
  {
    name = null;
    isLeftNavigable = aIsLeftNavigable;
    isRightNavigable = aIsRightNavigable;
    positions = new ArrayList<Coordinate>();
    tokenPosition = null;
    tokenEndPosition = null;
    immutable = false;
    ends = new ArrayList<AssociationEnd>();
    boolean didAddEnds = setEnds(allEnds);
    if (!didAddEnds)
    {
      throw new RuntimeException("Unable to create Association, must have 2 ends");
    }
    // line 535 "../../../../src/Umple.ump"
    this.setLeftAndRight();
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

  public boolean setIsLeftNavigable(boolean aIsLeftNavigable)
  {
    boolean wasSet = false;
    isLeftNavigable = aIsLeftNavigable;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsRightNavigable(boolean aIsRightNavigable)
  {
    boolean wasSet = false;
    isRightNavigable = aIsRightNavigable;
    wasSet = true;
    return wasSet;
  }

  public boolean addPosition(Coordinate aPosition)
  {
    boolean wasAdded = false;
    wasAdded = positions.add(aPosition);
    return wasAdded;
  }

  public boolean removePosition(Coordinate aPosition)
  {
    boolean wasRemoved = false;
    wasRemoved = positions.remove(aPosition);
    return wasRemoved;
  }

  public boolean setTokenPosition(Position aTokenPosition)
  {
    boolean wasSet = false;
    tokenPosition = aTokenPosition;
    wasSet = true;
    return wasSet;
  }

  public boolean setTokenEndPosition(Position aTokenEndPosition)
  {
    boolean wasSet = false;
    tokenEndPosition = aTokenEndPosition;
    wasSet = true;
    return wasSet;
  }

  /**
   * The name of the association.
   */
  @umplesourcefile(line={534},file={"Umple.ump"},javaline={129},length={1})
  public String getName()
  {
    // line 534 "../../../../src/Umple.ump"
    if (!isNamed()) { return this.deriveName(); }
    return name;
  }

  public boolean getIsLeftNavigable()
  {
    return isLeftNavigable;
  }

  public boolean getIsRightNavigable()
  {
    return isRightNavigable;
  }

  public Coordinate getPosition(int index)
  {
    Coordinate aPosition = positions.get(index);
    return aPosition;
  }

  public Coordinate[] getPositions()
  {
    Coordinate[] newPositions = positions.toArray(new Coordinate[positions.size()]);
    return newPositions;
  }

  public int numberOfPositions()
  {
    int number = positions.size();
    return number;
  }

  public boolean hasPositions()
  {
    boolean has = positions.size() > 0;
    return has;
  }

  public int indexOfPosition(Coordinate aPosition)
  {
    int index = positions.indexOf(aPosition);
    return index;
  }

  public Position getTokenPosition()
  {
    return tokenPosition;
  }

  public Position getTokenEndPosition()
  {
    return tokenEndPosition;
  }

  @umplesourcefile(line={532, 532},file={"Umple.ump", "Umple.ump"},javaline={186, 201},length={2, 2})
  public boolean getNamed()
  {
    return name != null && !"".equals(name);
  }

  public boolean isIsLeftNavigable()
  {
    return isLeftNavigable;
  }

  public boolean isIsRightNavigable()
  {
    return isRightNavigable;
  }

  public boolean isNamed()
  {
    return name != null && !"".equals(name);
  }

  public AssociationEnd getEnd(int index)
  {
    AssociationEnd aEnd = ends.get(index);
    return aEnd;
  }

  public List<AssociationEnd> getEnds()
  {
    List<AssociationEnd> newEnds = Collections.unmodifiableList(ends);
    return newEnds;
  }

  public int numberOfEnds()
  {
    int number = ends.size();
    return number;
  }

  public boolean hasEnds()
  {
    boolean has = ends.size() > 0;
    return has;
  }

  public int indexOfEnd(AssociationEnd aEnd)
  {
    int index = ends.indexOf(aEnd);
    return index;
  }

  public static int requiredNumberOfEnds()
  {
    return 2;
  }

  public static int minimumNumberOfEnds()
  {
    return 2;
  }

  public static int maximumNumberOfEnds()
  {
    return 2;
  }

  public boolean setEnds(AssociationEnd... newEnds)
  {
    boolean wasSet = false;
    ArrayList<AssociationEnd> checkNewEnds = new ArrayList<AssociationEnd>();
    for (AssociationEnd newEnd : newEnds)
    {
      if (checkNewEnds.contains(newEnd))
      {
        return wasSet;
      }
      else if (newEnd.getAssociation() != null && !this.equals(newEnd.getAssociation()))
      {
        return wasSet;
      }
      checkNewEnds.add(newEnd);
    }

    if (checkNewEnds.size() != minimumNumberOfEnds())
    {
      return wasSet;
    }

    ends.removeAll(checkNewEnds);
    
    for (AssociationEnd orphan : ends)
    {
      setAssociation(orphan, null);
    }
    ends.clear();
    for (AssociationEnd newEnd : newEnds)
    {
      setAssociation(newEnd, this);
      ends.add(newEnd);
    }
    wasSet = true;
    return wasSet;
  }

  private void setAssociation(AssociationEnd aEnd, Association aAssociation)
  {
    try
    {
      java.lang.reflect.Field mentorField = aEnd.getClass().getDeclaredField("association");
      mentorField.setAccessible(true);
      mentorField.set(aEnd, aAssociation);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aAssociation to aEnd", e);
    }
  }

  public void delete()
  {
    for(AssociationEnd aEnd : ends)
    {
      setAssociation(aEnd,null);
    }
    ends.clear();
  }

  @umplesourcefile(line={1241},file={"Umple_Code.ump"},javaline={312},length={12})
   public void setLeftAndRight(){
    String name = this.getName();

    int underscore = name.indexOf("__");
    String nameOne = name.substring(0,underscore);
    String nameTwo = name.substring(underscore + "__".length(), name.length());

    if (nameOne.compareTo(nameTwo) > 0)
    {

    }
  }

  @umplesourcefile(line={1256},file={"Umple_Code.ump"},javaline={326},length={19})
   public int whoIsInvalid(){
    if (!getIsLeftNavigable() || !getIsRightNavigable())
    {
      return -1;
    }

    if (getEnd(0).getClassName().equals(getEnd(1).getClassName()))
    {
      if (getEnd(0).getMultiplicity().getLowerBound() != 0)
      {
        return 0;
      }
      else if (getEnd(1).getMultiplicity().getLowerBound() != 0)
      {
        return 1;
      }
    }
    return -1;
  }

  @umplesourcefile(line={1277},file={"Umple_Code.ump"},javaline={347},length={3})
   public boolean isValid(){
    return whoIsInvalid() == -1;
  }

  @umplesourcefile(line={1282},file={"Umple_Code.ump"},javaline={352},length={16})
   public String getArrowString(){
    String arrow = "--";
    if (getIsLeftNavigable() && !getIsRightNavigable())
    {
      arrow = "<-";
    }
    else if (!getIsLeftNavigable() && getIsRightNavigable())
    {
      arrow = "->";
    }
    else if (!getIsLeftNavigable() && !getIsRightNavigable())
    {
      arrow = "><";
    }
    return arrow;
  }

  @umplesourcefile(line={1300},file={"Umple_Code.ump"},javaline={370},length={14})
   public String toGenericString(){
    String leftSide = getEnd(0).toGenericString();
    String rightSide = getEnd(1).toGenericString();


    if (leftSide.equals("n") && rightSide.equals("n") && !getEnd(0).toSimpleString().equals(getEnd(1).toSimpleString()))
    {
      return cruise.umple.util.StringFormatter.format("n {0} m",getArrowString());
    }
    else
    {
      return cruise.umple.util.StringFormatter.format("{0} {1} {2}",leftSide,getArrowString(),rightSide);
    }
  }

  @umplesourcefile(line={1316},file={"Umple_Code.ump"},javaline={386},length={19})
   public String deriveName(){
    AssociationEnd firstEnd = this.getEnd(0);
    AssociationEnd secondEnd = this.getEnd(1);

    String firstClassName = firstEnd.getClassName();
    String secondClassName = secondEnd.getClassName();

    String firstRole = firstEnd.getDisplayRoleName().equals("") ? "" : ":" + firstEnd.getDisplayRoleName();
    String secondRole = secondEnd.getDisplayRoleName().equals("") ? "" : ":" + secondEnd.getDisplayRoleName();

    if (firstClassName.compareTo(secondClassName) <= 0)
    {
      return firstClassName + firstRole + "__" + secondClassName + secondRole;
    }
    else
    {
      return secondClassName + secondRole + "__" + firstClassName + firstRole;
    }
  }

  @umplesourcefile(line={1337},file={"Umple_Code.ump"},javaline={407},length={3})
   public void setImmutable(){
    this.immutable = true;
  }

  @umplesourcefile(line={1342},file={"Umple_Code.ump"},javaline={412},length={3})
   public boolean isImmutable(){
    return this.immutable;
  }


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "isLeftNavigable" + ":" + getIsLeftNavigable()+ "," +
            "isRightNavigable" + ":" + getIsRightNavigable()+ "," +
            "named" + ":" + getNamed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "tokenPosition" + "=" + (getTokenPosition() != null ? !getTokenPosition().equals(this)  ? getTokenPosition().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "tokenEndPosition" + "=" + (getTokenEndPosition() != null ? !getTokenEndPosition().equals(this)  ? getTokenEndPosition().toString().replaceAll("  ","    ") : "this" : "null")
     + outputString;
  }
}