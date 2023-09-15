package com.veypo.meal_planner.pojo;

public class EntityVersion {

    private String entityId;
    private String recordVersion;
    private String lastModified;


    public EntityVersion(String entityId, String recordVersion, String lastModified) {
        this.entityId = entityId;
        this.recordVersion = recordVersion;
        this.lastModified = lastModified;
    }

    public String getEntityId() {
        return entityId;
    }

    public String getRecordVersion() {
        return recordVersion;
    }

    public String getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "EntityVersion{" +
                "entityId='" + entityId + '\'' +
                ", recordVersion='" + recordVersion + '\'' +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
