<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.15.0.1751 modeling language!*/

class MentorM
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MentorM Attributes
  private $name;

  //MentorM Associations
  private $students;
  private $program;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName)
  {
    $this->name = $aName;
    $this->students = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getStudent($index)
  {
    $aStudent = $this->students[$index];
    return $aStudent;
  }

  public function getStudents()
  {
    $newStudents = $this->students;
    return $newStudents;
  }

  public function numberOfStudents()
  {
    $number = count($this->students);
    return $number;
  }

  public function hasStudents()
  {
    $has = $this->numberOfStudents() > 0;
    return $has;
  }

  public function indexOfStudent($aStudent)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->students as $student)
    {
      if ($student->equals($aStudent))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getProgram()
  {
    return $this->program;
  }

  public function isNumberOfStudentsValid()
  {
    $isValid = $this->numberOfStudents() >= self::minimumNumberOfStudents();
    return $isValid;
  }

  public static function minimumNumberOfStudents()
  {
    return 2;
  }

  public function addStudentVia($aNumber)
  {
    return new StudentM($aNumber, $this);
  }

  public function addStudent($aStudent)
  {
    $wasAdded = false;
    if ($this->indexOfStudent($aStudent) !== -1) { return false; }
    $existingMentor = $aStudent->getMentor();
    $isNewMentor = $existingMentor != null && $this !== $existingMentor;

    if ($isNewMentor && $existingMentor->numberOfStudents() <= self::minimumNumberOfStudents())
    {
      return $wasAdded;
    }

    if ($isNewMentor)
    {
      $aStudent->setMentor($this);
    }
    else
    {
      $this->students[] = $aStudent;
    }
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeStudent($aStudent)
  {
    $wasRemoved = false;
    //Unable to remove aStudent, as it must always have a mentor
    if ($this === $aStudent->getMentor())
    {
      return $wasRemoved;
    }

    //mentor already at minimum (2)
    if ($this->numberOfStudents() <= self::minimumNumberOfStudents())
    {
      return $wasRemoved;
    }

    unset($this->students[$this->indexOfStudent($aStudent)]);
    $this->students = array_values($this->students);
    $wasRemoved = true;
    return $wasRemoved;
  }

  public function setProgram($newProgram)
  {
    $wasSet = false;
    if ($newProgram == null)
    {
      $existingProgram = $this->program;
      $this->program = null;
      
      if ($existingProgram != null && $existingProgram->getMentor() != null)
      {
        $existingProgram->setMentor(null);
      }
      $wasSet = true;
      return $wasSet;
    }
    
    $currentProgram = $this->getProgram();
    if ($currentProgram != null && $currentProgram != $newProgram)
    {
      $currentProgram->setMentor(null);
    }
    
    $this->program = $newProgram;
    $existingMentor = $newProgram->getMentor();
    
    if ($this != $existingMentor)
    {
      $newProgram->setMentor($this);
    }
    $wasSet = true;
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    foreach ($this->students as $aStudent)
    {
      $aStudent->delete();
    }
    if ($this->program != null)
    {
      $this->program->setMentor(null);
    }
  }

}
?>