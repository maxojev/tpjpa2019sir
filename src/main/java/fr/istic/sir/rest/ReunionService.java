package fr.istic.sir.rest;

import DAO.ReunionDao;
import DAO.UserDao;
import jpa.Lieu;
import jpa.Personne;
import jpa.Reunion;
import jpa.Sdate;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Path("/ReunionService")
public class ReunionService {

    Reunion reunion;
    ReunionDao reunionDao =new ReunionDao();
    UserDao userDao=new UserDao();
    Collection<Sdate> datesProposees =new HashSet<Sdate>();
    Collection<Lieu> lieuProposees = new HashSet<Lieu>();
    Personne personne;



    @POST
    @Path("/addReunion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion addReunion(JSONObject reu) throws JSONException, ParseException {

        if(reu.getString("reunionType").equalsIgnoreCase("option1")){

            datesProposees= createSetDateProposeForReunion(reu);
            lieuProposees=  createSetLieuProposeForReunion(reu);

        }else if(reu.getString("reunionType").equalsIgnoreCase("option2")){
            System.out.println("monoption2");
            datesProposees= createSetDateProposeForReunion(reu);
            lieuProposees=null;
        }else if(reu.getString("reunionType").equalsIgnoreCase("option3")){
            System.out.println("monoption3");
            lieuProposees=  createSetLieuProposeForReunion(reu);
            datesProposees=null;
        }

        personne = new Personne(reu.getJSONObject("createur").getString("nom"),reu.getJSONObject("createur").getString("prenom"),reu.getJSONObject("createur").getString("mail"));

        Personne p= userDao.getUser(personne);

        reunion = new Reunion(reu.getString("intitule"),reu.getString("resume"),p,datesProposees,lieuProposees);

        return reunionDao.addReunion(reunion);


    }

    @GET
    @Path("/getAllReunion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reunion> getAllReunion(){
        return reunionDao.getAllReunion();
    }

    @POST
    @Path("/getOneReunion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Reunion getOneReunion(JSONObject reunion) throws JSONException {


        return reunionDao.getOneReunion(reunion.getLong("idReunion"));
    }

    private Date stringToDate (String value) throws ParseException {
        DateFormat df= new SimpleDateFormat("yyyy-mm-dd");
        java.util.Date d = df.parse(value);
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

    private Boolean stringCheckBoxToBoolean(String value){

        return !(value!=null);
    }

    private Collection<Sdate> createSetDateProposeForReunion(JSONObject reu) throws JSONException, ParseException {


        Collection<Sdate> d =new HashSet<Sdate>();

        Date date1 = stringToDate(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause1").getString("date1"));
        Boolean pause1 = stringCheckBoxToBoolean(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause1").getString("pause1"));
        d.add(new Sdate(date1,pause1));

        Date date2 = stringToDate(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause2").getString("date2"));
        Boolean pause2 = stringCheckBoxToBoolean(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause2").getString("pause2"));
        d.add(new Sdate(date2,pause2));

        Date date3 = stringToDate(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause3").getString("date3"));
        Boolean pause3 = stringCheckBoxToBoolean(reu.getJSONObject("mesDatesPauses").getJSONObject("DatePause3").getString("pause3"));
        d.add(new Sdate(date3,pause3));

        return d;
    }

    private Collection<Lieu> createSetLieuProposeForReunion(JSONObject reu) throws JSONException {


        Collection<Lieu> l = new HashSet<Lieu>();

        l.add(new Lieu(reu.getJSONObject("mesLieux").getString("lieu1")));
        l.add(new Lieu(reu.getJSONObject("mesLieux").getString("lieu2")));
        l.add(new Lieu(reu.getJSONObject("mesLieux").getString("lieu3")));

        return l;
    }
}
