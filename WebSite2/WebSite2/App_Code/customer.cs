using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;
using System.Data;
using System.Web.Script.Serialization;

/// <summary>
/// Summary description for customer
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class customer : System.Web.Services.WebService {

    string conString = "Data Source=sql5103.site4now.net;Initial Catalog=db_a7b488_adityajaitpal22;Persist Security Info=True;User ID=db_a7b488_adityajaitpal22_admin;Password=aditya2012";  


    public customer () {

        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }



    [WebMethod]
    public void registerUser(string User_Name, string Phone, string Email, string Password, string AddressLine, string Country, string State,
    string District, string Area, string Pincode, string City, string Sub_Localoty, string Premises, string Latitude, string Longitude
   ,string User_Status, string Remark, string Remark_, string Extra, string DOB,string Alternate_Mobile)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[UserMaster] ([User_Name],[Phone] ,[Email],[Password],[AddressLine],[Country],[State],[District] ,[Area],[Pincode],[City],[Sub_Localoty],[Premises],[Latitude],[Longitude],[User_Status],[Remark],[Remark_] ,[Extra],[DOB] ,[Alternate_Mobile],[Added_On]) VALUES "+
 "(@User_Name,@Phone, @Email, @Password,@AddressLine,@Country,@State,@District,@Area,@Pincode,@City,@Sub_Localoty,@Premises, "+
 "@Latitude,@Longitude,@User_Status,@Remark,@Remark_,@Extra, @DOB,@Alternate_Mobile,@Added_On)", con, transaction);

            cmd.Parameters.AddWithValue("@User_Name", User_Name);
            cmd.Parameters.AddWithValue("@Phone", Phone);

            cmd.Parameters.AddWithValue("@Email", Email);
            cmd.Parameters.AddWithValue("@Password", Password);
            cmd.Parameters.AddWithValue("@AddressLine", AddressLine);
            cmd.Parameters.AddWithValue("@Country", Country);
            cmd.Parameters.AddWithValue("@State", State);
            cmd.Parameters.AddWithValue("@District", District);
            cmd.Parameters.AddWithValue("@Area", Area);
            cmd.Parameters.AddWithValue("@Pincode", Pincode);
            cmd.Parameters.AddWithValue("@City", City);
            cmd.Parameters.AddWithValue("@Sub_Localoty", Sub_Localoty);
            cmd.Parameters.AddWithValue("@Premises", Premises);
            cmd.Parameters.AddWithValue("@Latitude", Latitude);
            cmd.Parameters.AddWithValue("@Longitude", Longitude);
            cmd.Parameters.AddWithValue("@User_Status", User_Status);
            cmd.Parameters.AddWithValue("@Remark", Remark);
            cmd.Parameters.AddWithValue("@Remark_", Remark_);
            cmd.Parameters.AddWithValue("@Extra", Extra);
            cmd.Parameters.AddWithValue("@DOB", DOB);
            cmd.Parameters.AddWithValue("@Alternate_Mobile", Alternate_Mobile);
            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("dd/MM/yyyy hh:mm:ss tt"));
            cmd.ExecuteNonQuery();


            transaction.Commit();

            arr = "Success";
            //          GKNotify.SendToOne("Remarks", "Remarks - " + Remarks, ID);

        }
        catch (SqlException sq)
        {
            transaction.Rollback();
            arr = "Unsuccessfull  SQL";
        }
        catch (Exception e)
        {
            arr = "Unsuccessfull";
        }
        finally
        {
            con.Close();
        }

        Dictionary<string, object> result = new Dictionary<string, object>();

        result.Add("POST_REQUEST_STATUS", arr);

        Context.Response.Write(new JavaScriptSerializer().Serialize(result));

    }





    [WebMethod]
    public void verifyLogin(string Phone, string password)
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT *  FROM [db_a7b488_adityajaitpal22].[dbo].[UserMaster] where [Phone]=@Phone and [Password]=@Password and User_Status='Active'", con);
        cmd.Parameters.AddWithValue("@Phone", Phone);
        cmd.Parameters.AddWithValue("@Password", password);


        List<Dictionary<string, object>> list = new List<Dictionary<string, object>>();

        try
        {
            con.Open();

            DataTable dt = new DataTable();

            new SqlDataAdapter(cmd).Fill(dt);


            if (dt.Rows.Count > 0)
            {
                Dictionary<string, object> row;

                foreach (DataRow dr in dt.Rows)
                {
                    row = new Dictionary<string, object>();

                    foreach (DataColumn col in dt.Columns)
                    {
                        row.Add(col.ColumnName, dr[col]);
                    }
                    list.Add(row);
                }
            }

        }
        catch (Exception e) { }
        finally
        {
            con.Close();
        }

        Context.Response.Write(new JavaScriptSerializer().Serialize(list));

    }



    [WebMethod]
    public void addHome(string User_Id, string Owner_Name, string Owner_Phone, string House_Number, string Landmark, string Area, string City,
    string District, string State, string Country, string Pincode, string AddressLine, string Latitude, string Longitude, string Is_by_location,
    string Room_Type, string Photo1, string Photo2, string Photo3, string Room_Availability, string Floor, string Building_Name, string Description,
    string Sale_Type, string Amount
   , string Sq_foot, string Water_Availability, string Lift_Availability, string Parking_Availability, string Remark, string Remark_,string Direction)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[House] ([User_Id] ,[Owner_Name] ,[Owner_Phone] ,[House_Number],[Landmark],[Area] ,[City] ,[District] ,[State],[Country] ,[Pincode] ,[AddressLine],[Latitude] ,[Longitude] ,[Is_by_location],[Room_Type],[Photo1] ,[Photo2],[Photo3]  ,[Room_Availability] ,[Floor],[Building_Name],[Description],[Sale_Type],[Amount],[Sq_foot],[Water_Availability],[Lift_Availability],[Parking_Availability],[Remark],[Remark_],[Added_On],[Direction]) VALUES" +
"(@User_Id,@Owner_Name,@Owner_Phone,@House_Number, @Landmark, @Area,@City, @District,@State, @Country, @Pincode,"+
"@AddressLine,@Latitude,@Longitude, @Is_by_location, @Room_Type,@Photo1, @Photo2, @Photo3, @Room_Availability,@Floor,"+
" @Building_Name,@Description,@Sale_Type,@Amount,@Sq_foot,@Water_Availability, @Lift_Availability, @Parking_Availability"+
" ,@Remark, @Remark_, @Added_On,@Direction)", con, transaction);

            cmd.Parameters.AddWithValue("@User_Id", User_Id);
            cmd.Parameters.AddWithValue("@Owner_Name", Owner_Name);

            cmd.Parameters.AddWithValue("@Owner_Phone", Owner_Phone);
            cmd.Parameters.AddWithValue("@House_Number", House_Number);
            cmd.Parameters.AddWithValue("@Landmark", Landmark);
            cmd.Parameters.AddWithValue("@Area", Area);
            cmd.Parameters.AddWithValue("@City", City);
            cmd.Parameters.AddWithValue("@District", District);
            cmd.Parameters.AddWithValue("@State", State);
            cmd.Parameters.AddWithValue("@Country", Country);
            cmd.Parameters.AddWithValue("@Pincode", Pincode);
            cmd.Parameters.AddWithValue("@AddressLine", AddressLine);
            cmd.Parameters.AddWithValue("@Latitude", Latitude);
            cmd.Parameters.AddWithValue("@Longitude", Longitude);
            cmd.Parameters.AddWithValue("@Is_by_location", Is_by_location);
            cmd.Parameters.AddWithValue("@Room_Type", Room_Type);
            cmd.Parameters.AddWithValue("@Photo1", Photo1);
            cmd.Parameters.AddWithValue("@Photo2", Photo2);
            cmd.Parameters.AddWithValue("@Photo3", Photo3);
            cmd.Parameters.AddWithValue("@Room_Availability", Room_Availability);
            cmd.Parameters.AddWithValue("@Floor", Floor);
            cmd.Parameters.AddWithValue("@Building_Name", Building_Name);
            cmd.Parameters.AddWithValue("@Description", Description);
            cmd.Parameters.AddWithValue("@Sale_Type", Sale_Type);
            cmd.Parameters.AddWithValue("@Amount", Amount);
            cmd.Parameters.AddWithValue("@Sq_foot", Sq_foot);
            cmd.Parameters.AddWithValue("@Water_Availability", Water_Availability);
            cmd.Parameters.AddWithValue("@Lift_Availability", Lift_Availability);
            cmd.Parameters.AddWithValue("@Parking_Availability", Parking_Availability);
            cmd.Parameters.AddWithValue("@Direction", Direction);

            cmd.Parameters.AddWithValue("@Remark", Remark);
            cmd.Parameters.AddWithValue("@Remark_", Remark_);
            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("dd/MM/yyyy hh:mm:ss tt"));
            cmd.ExecuteNonQuery();


            transaction.Commit();

            arr = "Success";
            //          GKNotify.SendToOne("Remarks", "Remarks - " + Remarks, ID);

        }
        catch (SqlException sq)
        {
            transaction.Rollback();
            arr = "Unsuccessfull  SQL";
        }
        catch (Exception e)
        {
            arr = "Unsuccessfull";
        }
        finally
        {
            con.Close();
        }

        Dictionary<string, object> result = new Dictionary<string, object>();

        result.Add("POST_REQUEST_STATUS", arr);

        Context.Response.Write(new JavaScriptSerializer().Serialize(result));

    }





    [WebMethod]
    public void getRoomsData()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7b488_adityajaitpal22].[dbo].[House] Order By Home_Id Desc", con);



        List<Dictionary<string, object>> list = new List<Dictionary<string, object>>();

        try
        {
            con.Open();

            DataTable dt = new DataTable();

            new SqlDataAdapter(cmd).Fill(dt);


            if (dt.Rows.Count > 0)
            {
                Dictionary<string, object> row;

                foreach (DataRow dr in dt.Rows)
                {
                    row = new Dictionary<string, object>();

                    foreach (DataColumn col in dt.Columns)
                    {
                        row.Add(col.ColumnName, dr[col]);
                    }
                    list.Add(row);
                }
            }

        }
        catch (Exception e) { }
        finally
        {
            con.Close();
        }

        Context.Response.Write(new JavaScriptSerializer().Serialize(list));

    }








    [WebMethod]
    public void getchats()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT *  FROM [db_a7b488_adityajaitpal22].[dbo].[Chats]", con);
     


        List<Dictionary<string, object>> list = new List<Dictionary<string, object>>();

        try
        {
            con.Open();

            DataTable dt = new DataTable();

            new SqlDataAdapter(cmd).Fill(dt);


            if (dt.Rows.Count > 0)
            {
                Dictionary<string, object> row;

                foreach (DataRow dr in dt.Rows)
                {
                    row = new Dictionary<string, object>();

                    foreach (DataColumn col in dt.Columns)
                    {
                        row.Add(col.ColumnName, dr[col]);
                    }
                    list.Add(row);
                }
            }

        }
        catch (Exception e) { }
        finally
        {
            con.Close();
        }

        Context.Response.Write(new JavaScriptSerializer().Serialize(list));

    }



    [WebMethod]
    public void addMSG_Connection(string Owner_Id, string User_Id, string Owner_Name, string User_Name, string Last_Message, string MSG_Status, string Remark)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[MSG_Connection] ([Owner_Id] ,[User_Id],[Owner_Name],[User_Name],[Last_Message],[MSG_Status],[Added_On],[Remark]) VALUES " +
                      "(@Owner_Id,@User_Id,@Owner_Name,@User_Name,@Last_Message,@MSG_Status,@Added_On, @Remark)", con, transaction);
            cmd.Parameters.AddWithValue("@Owner_Id", Owner_Id);
            cmd.Parameters.AddWithValue("@User_Id", User_Id);
            cmd.Parameters.AddWithValue("@Owner_Name", Owner_Name);
            cmd.Parameters.AddWithValue("@User_Name", User_Name);
            cmd.Parameters.AddWithValue("@Last_Message", Last_Message);
            cmd.Parameters.AddWithValue("@MSG_Status", MSG_Status);
            cmd.Parameters.AddWithValue("@Remark", Remark);
            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("dd/MM/yyyy hh:mm:ss tt"));

            cmd.ExecuteNonQuery();


            transaction.Commit();

            arr = "Success";
            //          GKNotify.SendToOne("Remarks", "Remarks - " + Remarks, ID);

        }
        catch (SqlException sq)
        {
            transaction.Rollback();
            arr = "Unsuccessfull  SQL";
        }
        catch (Exception e)
        {
            arr = "Unsuccessfull";
        }
        finally
        {
            con.Close();
        }

        Dictionary<string, object> result = new Dictionary<string, object>();

        result.Add("POST_REQUEST_STATUS", arr);

        Context.Response.Write(new JavaScriptSerializer().Serialize(result));

    }







    [WebMethod]
    public void getMSG_Connection()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7b488_adityajaitpal22].[dbo].[MSG_Connection] Order by Message_Id Desc", con);



        List<Dictionary<string, object>> list = new List<Dictionary<string, object>>();

        try
        {
            con.Open();

            DataTable dt = new DataTable();

            new SqlDataAdapter(cmd).Fill(dt);


            if (dt.Rows.Count > 0)
            {
                Dictionary<string, object> row;

                foreach (DataRow dr in dt.Rows)
                {
                    row = new Dictionary<string, object>();

                    foreach (DataColumn col in dt.Columns)
                    {
                        row.Add(col.ColumnName, dr[col]);
                    }
                    list.Add(row);
                }
            }

        }
        catch (Exception e) { }
        finally
        {
            con.Close();
        }

        Context.Response.Write(new JavaScriptSerializer().Serialize(list));

    }


}
