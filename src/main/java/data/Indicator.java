package main.java.data;

import javafx.beans.property.StringProperty;

public class Indicator{

    private StringProperty code;
    private StringProperty name;
    private StringProperty sourceID;
    private StringProperty sourceNote;
    private StringProperty topicID;
    private StringProperty topicName;

    public Indicator(){}


    public String getCode() {
        return code.get();
    }

    public StringProperty codeProperty() {
        return code;
    }

    public void setCode(String code) {
        this.code.set(code);
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

    public String getTopicID() {
        return topicID.get();
    }

    public StringProperty topicIDProperty() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID.set(topicID);
    }

    public String getTopicName() {
        return topicName.get();
    }

    public StringProperty topicNameProperty() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName.set(topicName);
    }


}
