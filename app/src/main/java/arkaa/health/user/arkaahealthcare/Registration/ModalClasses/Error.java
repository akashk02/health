
package arkaa.health.user.arkaahealthcare.Registration.ModalClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("severity")
    @Expose
    private String severity;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("detail")
    @Expose
    private String detail;
    @SerializedName("where")
    @Expose
    private String where;
    @SerializedName("schema")
    @Expose
    private String schema;
    @SerializedName("table")
    @Expose
    private String table;
    @SerializedName("constraint")
    @Expose
    private String constraint;
    @SerializedName("file")
    @Expose
    private String file;
    @SerializedName("line")
    @Expose
    private String line;
    @SerializedName("routine")
    @Expose
    private String routine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

}
