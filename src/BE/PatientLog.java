package BE;

public class PatientLog {

    private int id;
    private String mestring;
    private String motivation;
    private String ressourcer;
    private String roller;
    private String vaner;
    private String uddannelse;
    private String livshistorie;
    private String netværk;
    private String helbredsoplysninger;
    private String hjælpemidler;
    private String boligens ;
    private int patientid ;

    public PatientLog(int id, String mestring, String motivation, String ressourcer, String roller, String vaner, String uddannelse,
                      String livshistorie, String netværk, String helbredsoplysninger, String hjælpemidler, String boligens , int pateintid) {
        this.id = id;
        this.mestring = mestring;
        this.motivation = motivation;
        this.ressourcer = ressourcer;
        this.roller = roller;
        this.vaner = vaner;
        this.uddannelse = uddannelse;
        this.livshistorie = livshistorie;
        this.netværk = netværk;
        this.helbredsoplysninger = helbredsoplysninger;
        this.hjælpemidler = hjælpemidler;
        this.boligens = boligens;
        this.patientid = pateintid ;
    }

    public int getPatientid() {
        return patientid;
    }

    public void setPatientid(int patientid) {
        this.patientid = patientid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMestring() {
        return mestring;
    }

    public void setMestring(String mestring) {
        this.mestring = mestring;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getRessourcer() {
        return ressourcer;
    }

    public void setRessourcer(String ressourcer) {
        this.ressourcer = ressourcer;
    }

    public String getRoller() {
        return roller;
    }

    public void setRoller(String roller) {
        this.roller = roller;
    }

    public String getVaner() {
        return vaner;
    }

    public void setVaner(String vaner) {
        this.vaner = vaner;
    }

    public String getUddannelse() {
        return uddannelse;
    }

    public void setUddannelse(String uddannelse) {
        this.uddannelse = uddannelse;
    }

    public String getLivshistorie() {
        return livshistorie;
    }

    public void setLivshistorie(String livshistorie) {
        this.livshistorie = livshistorie;
    }

    public String getNetværk() {
        return netværk;
    }

    public void setNetværk(String netværk) {
        this.netværk = netværk;
    }

    public String getHelbredsoplysninger() {
        return helbredsoplysninger;
    }

    public void setHelbredsoplysninger(String helbredsoplysninger) {
        this.helbredsoplysninger = helbredsoplysninger;
    }

    public String getHjælpemidler() {
        return hjælpemidler;
    }

    public void setHjælpemidler(String hjælpemidler) {
        this.hjælpemidler = hjælpemidler;
    }

    public String getBoligens() {
        return boligens;
    }

    public void setBoligens(String boligens) {
        this.boligens = boligens;
    }

    @Override
    public String toString() {
        return "PatientLog{" +
                "id=" + id +
                ", mestring='" + mestring + '\'' +
                ", motivation='" + motivation + '\'' +
                ", ressourcer='" + ressourcer + '\'' +
                ", roller='" + roller + '\'' +
                ", vaner='" + vaner + '\'' +
                ", uddannelse='" + uddannelse + '\'' +
                ", livshistorie='" + livshistorie + '\'' +
                ", netværk='" + netværk + '\'' +
                ", helbredsoplysninger='" + helbredsoplysninger + '\'' +
                ", hjælpemidler='" + hjælpemidler + '\'' +
                ", boligens='" + boligens + '\'' +
                '}';
    }
}
