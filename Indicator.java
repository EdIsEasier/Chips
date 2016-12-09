package main.java.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Indicator{

    private StringProperty id;
    private StringProperty name;
    private StringProperty sourceID;
    private StringProperty sourceNote;
    private StringProperty sourceOrganization;
    private StringProperty topicsID;
    private StringProperty topicsValue;


    public Indicator(){
        this.id = new SimpleStringProperty("id");
        this.name = new SimpleStringProperty("name");
        this.sourceID = new SimpleStringProperty("sourceID");
        this.sourceNote = new SimpleStringProperty("sourceNote");
        this.topicsID = new SimpleStringProperty("topicsID");
        this.topicsValue = new SimpleStringProperty("topicsValue");
    }

    public Indicator(String id, String name, String sourceID, String sourceNote, String sourceOrganization, String topicsID, String topicsValue){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.sourceID = new SimpleStringProperty(sourceID);
        this.sourceNote = new SimpleStringProperty(sourceNote);
        this.sourceOrganization = new SimpleStringProperty(sourceOrganization);
        this.topicsID = new SimpleStringProperty(topicsID);
        this.topicsValue = new SimpleStringProperty(topicsValue);
    }

    public String getSourceOrganization() {
        return sourceOrganization.get();
    }

    public StringProperty sourceOrganizationProperty() {
        return sourceOrganization;
    }

    public void setSourceOrganization(String sourceOrganization) {
        this.sourceOrganization.set(sourceOrganization);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSourceID() {
        return sourceID.get();
    }

    public StringProperty sourceIDProperty() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID.set(sourceID);
    }

    public String getSourceNote() {
        return sourceNote.get();
    }

    public StringProperty sourceNoteProperty() {
        return sourceNote;
    }

    public void setSourceNote(String sourceNote) {
        this.sourceNote.set(sourceNote);
    }

    public String getTopicsID() {
        return topicsID.get();
    }

    public StringProperty topicsIDProperty() {
        return topicsID;
    }

    public void setTopicsID(String topicsID) {
        this.topicsID.set(topicsID);
    }

    public String getTopicName() {
        return topicsValue.get();
    }

    public StringProperty topicNameProperty() {
        return topicsValue;
    }

    public void setTopicName(String topicName) {
        this.topicsValue.set(topicName);
    }


}
