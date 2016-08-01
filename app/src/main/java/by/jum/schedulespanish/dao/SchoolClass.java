package by.jum.schedulespanish.dao;

import java.util.List;

import by.jum.schedulespanish.models.SchoolClassModel;

public interface SchoolClass {

    boolean addClass(SchoolClassModel schoolClassModel);

    List<SchoolClassModel> getAllClasses();

    SchoolClassModel getClassByID(int id);

    public SchoolClassModel getClassByName(String name);

    public void deleteByID(int id);

    public void deleteByName(String name);
}
