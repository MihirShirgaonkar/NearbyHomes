using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;
using System.Data;
using System.Web.Script.Serialization;
using System.Globalization;

using System.IO;
using System.Collections;
using System.Web.Services.Protocols;
using System.ComponentModel;

/// <summary>
/// Summary description for admin
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class admin : System.Web.Services.WebService {

    string conString = "Data Source=SQL5104.site4now.net;Initial Catalog=db_a7ae0e_rohitmewada;Persist Security Info=True;User ID=db_a7ae0e_rohitmewada_admin;Password=rohit2012";

                                                         
     public admin() { }



    [WebMethod]
    public void UploadFile2(byte[] f, string fileName)
    {
        string arr;
        try
        {

            MemoryStream ms = new MemoryStream(f);

            FileStream fs = new FileStream(System.Web.Hosting.HostingEnvironment.MapPath
                        ("~/room_images/") + fileName, FileMode.Create);

            ms.WriteTo(fs);

            ms.Close();
            fs.Close();
            fs.Dispose();
            arr = "Success";

        }
        catch (Exception ex)
        {

            arr = "Unsuccessfull";
        }


        Dictionary<string, object> result = new Dictionary<string, object>();

        result.Add("POST_REQUEST_STATUS", arr);

        Context.Response.Write(new JavaScriptSerializer().Serialize(result));
    }


    [WebMethod]
     public void getStandards()
     {


         SqlConnection con = new SqlConnection(conString);

         SqlCommand cmd = new SqlCommand("SELECT *  FROM [db_a7ae0e_rohitmewada].[dbo].[Standards]", con);
       

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
    public void getAttandance()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Attendance]", con);


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
    public void getPayments()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Payment] ORDER BY Sr_No DESC", con);


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
    public void getFacultyLectures()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Lectures] ORDER BY Lecture_Id DESC", con);


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
    public void getAttendanceStudents(string STD_ID,string Att_Date)
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Attendance] Where STD_ID=@STD_ID AND Att_Date=@Att_Date", con);
        cmd.Parameters.AddWithValue("@STD_ID", STD_ID);
        cmd.Parameters.AddWithValue("@Att_Date", Att_Date);



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
    public void getStudentsByStudentMaster()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[STUDENT_MASTER] WHERE Admin_Approval='Yes'", con);


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
    public void getTeachers()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Teachers]", con);


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
    public void verifyLoginStudent(string username, string password)
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[STUDENT_MASTER] where Username=@Username	and [Password]=@Password and Admin_Approval='Yes'", con);
        cmd.Parameters.AddWithValue("@Username", username);
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
    public void verifyLoginAdmin(string username, string password)
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[ADMIN_MASTER] where Username=@Username and [Password]=@Password", con);
        cmd.Parameters.AddWithValue("@Username", username);
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
    public void studentRegestration(string Student_Name, string Mobile, string STD, string STD_ID, string Email, string Admin_Approval, string Username, string Password, string Address, string Photo, string Fees, string About, string About_, string DOB, string Gender)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[STUDENT_MASTER] ([Student_Name] ,[Mobile] ,[STD] ,[STD_ID] ,[Email] ,[Admin_Approval] ,[Username] ,[Password],[Address],[Photo] ,[Added_On] ,[Fees] ,[About] ,[About_] ,[DOB] ,[Gender]) VALUES"+
" (@Student_Name,@Mobile,@STD,@STD_ID,@Email,@Admin_Approval,@Username,@Password,@Address,@Photo,@Added_On,@Fees,@About,@About_,@DOB,@Gender)", con, transaction);

            cmd.Parameters.AddWithValue("@Student_Name", Student_Name);
            cmd.Parameters.AddWithValue("@Mobile", Mobile);

            cmd.Parameters.AddWithValue("@STD", STD);
            cmd.Parameters.AddWithValue("@STD_ID", STD_ID);
            cmd.Parameters.AddWithValue("@Email", Email);
            cmd.Parameters.AddWithValue("@Admin_Approval", Admin_Approval);
            cmd.Parameters.AddWithValue("@Username", Username);
            cmd.Parameters.AddWithValue("@Password", Password);
            cmd.Parameters.AddWithValue("@Address", Address);
            cmd.Parameters.AddWithValue("@Photo", Photo);
            cmd.Parameters.AddWithValue("@Fees", Fees);
            cmd.Parameters.AddWithValue("@About", About);
            cmd.Parameters.AddWithValue("@About_", About_);
            cmd.Parameters.AddWithValue("@DOB", DOB);
            cmd.Parameters.AddWithValue("@Gender", Gender);

            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("yyyy/MM/dd hh:mm:ss tt"));
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
    public void studentAproval( string Student_Id, string Aproval)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("UPDATE [dbo].[STUDENT_MASTER] SET  [Admin_Approval] = @Aproval WHERE STUDENT_ID=@Student_Id", con, transaction);

            cmd.Parameters.AddWithValue("@Aproval", Aproval);
            cmd.Parameters.AddWithValue("@Student_Id", Student_Id);
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
    public void getNonAproved()
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[STUDENT_MASTER] where [Admin_Approval]='No' Order By STUDENT_ID DESC", con);


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
    public void addLecture(string Standard_Id, string Standard_Name, string Lecturer, string Subject_Name, string Topic_Name, string Lecture_Date, string Meeting_ID, string Meeting_Password, string Zoom_ID, string Zoom_Password, string Remark)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[Lectures] ([Standard_Id] ,[Standard_Name] ,[Lecturer] ,[Subject_Name] ,[Topic_Name],[Lecture_Date],[Meeting_ID] ,[Meeting_Password] ,[Zoom_ID] ,[Zoom_Password],[Remark]) VALUES"+
 "(@Standard_Id,@Standard_Name,@Lecturer,@Subject_Name,@Topic_Name,@Lecture_Date,@Meeting_ID,@Meeting_Password,@Zoom_ID,@Zoom_Password,@Remark)", con, transaction);

            cmd.Parameters.AddWithValue("@Standard_Id", Standard_Id);
            cmd.Parameters.AddWithValue("@Standard_Name", Standard_Name);

            cmd.Parameters.AddWithValue("@Lecturer", Lecturer);
            cmd.Parameters.AddWithValue("@Subject_Name", Subject_Name);
            cmd.Parameters.AddWithValue("@Topic_Name", Topic_Name);
            cmd.Parameters.AddWithValue("@Lecture_Date", Lecture_Date);
            cmd.Parameters.AddWithValue("@Meeting_ID", Meeting_ID);
            cmd.Parameters.AddWithValue("@Meeting_Password", Meeting_Password);
            cmd.Parameters.AddWithValue("@Zoom_ID", Zoom_ID);
            cmd.Parameters.AddWithValue("@Zoom_Password", Zoom_Password);
            cmd.Parameters.AddWithValue("@Remark", Remark);
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
    public void getLectures(string Standard_Id)
    {


        SqlConnection con = new SqlConnection(conString);

        SqlCommand cmd = new SqlCommand("SELECT * FROM [db_a7ae0e_rohitmewada].[dbo].[Lectures] WHERE Standard_Id=@Standard_Id ORDER BY Lecture_Id DESC", con);
        cmd.Parameters.AddWithValue("@Standard_Id", Standard_Id);



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
    public void addAttendance(string Student_Id, string Student_Name, string Att_Status, string STD_ID, string STD_Name, string Remark)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[Attendance]  ([Student_Id]  ,[Student_Name] ,[Att_Status] ,[Att_Date] ,[STD_ID] ,[STD_Name] ,[Remark])"+
                " VALUES  (@Student_Id ,@Student_Name,@Att_Status,@Att_Date,@STD_ID,@STD_Name, @Remark)", con, transaction);

            cmd.Parameters.AddWithValue("@Student_Id", Student_Id);
            cmd.Parameters.AddWithValue("@Student_Name", Student_Name);

            cmd.Parameters.AddWithValue("@Att_Status", Att_Status);
            cmd.Parameters.AddWithValue("@Att_Date", DateTime.Now.ToString("dd/MM/yyyy"));
            cmd.Parameters.AddWithValue("@STD_ID", STD_ID);
            cmd.Parameters.AddWithValue("@STD_Name", STD_Name);
            cmd.Parameters.AddWithValue("@Remark", Remark);
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
    public void addPayment(string Student_Id, string Payment_Id, string Order_Id, string Payment_Status, string Amount,string Student_Name,string STD,string Fees_Type,string Remark,string Mobile,string Email,string Updated_Amount)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


 SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[Payment] ([Student_Id] ,[Payment_Id],[Order_Id] ,[Payment_Status] ,[Amount],[Added_On],[Student_Name] ,[STD] ,[Fees_Type] ,[Remark],[Mobile] ,[Email]) VALUES "+
"(@Student_Id,@Payment_Id,@Order_Id,@Payment_Status, @Amount,@Added_On,@Student_Name,@STD,@Fees_Type,@Remark,@Mobile,@Email)", con, transaction);

            cmd.Parameters.AddWithValue("@Student_Id", Student_Id);
            cmd.Parameters.AddWithValue("@Payment_Id", Payment_Id);

            cmd.Parameters.AddWithValue("@Order_Id", Order_Id);
            cmd.Parameters.AddWithValue("@Payment_Status", Payment_Status);
            cmd.Parameters.AddWithValue("@Amount", Amount);
            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("dd/MM/yyyy hh:mm:ss tt"));


            cmd.Parameters.AddWithValue("@STD", STD);
            cmd.Parameters.AddWithValue("@Remark", Remark);
            cmd.Parameters.AddWithValue("@Student_Name", Student_Name);
            cmd.Parameters.AddWithValue("@Fees_Type", Fees_Type);
            cmd.Parameters.AddWithValue("@Mobile", Mobile);
            cmd.Parameters.AddWithValue("@Email", Email);
            cmd.ExecuteNonQuery();


            cmd = new SqlCommand("UPDATE [dbo].[STUDENT_MASTER] SET [About]=@About WHERE STUDENT_ID=@STUDENT_ID", con, transaction);
            cmd.Parameters.AddWithValue("@About", Updated_Amount);
            cmd.Parameters.AddWithValue("@STUDENT_ID", Student_Id);

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
    public void addPaymentFailed(string Student_Id, string Payment_Id, string Order_Id, string Payment_Status, string Amount, string Student_Name, string STD, string Fees_Type, string Remark, string Mobile, string Email)
    {
        string arr;
        SqlConnection con = new SqlConnection(conString);
        SqlTransaction transaction;
        con.Open();
        transaction = con.BeginTransaction();
        try
        {


            SqlCommand cmd = new SqlCommand("INSERT INTO [dbo].[Payment] ([Student_Id] ,[Payment_Id],[Order_Id] ,[Payment_Status] ,[Amount],[Added_On],[Student_Name] ,[STD] ,[Fees_Type] ,[Remark],[Mobile] ,[Email]) VALUES " +
           "(@Student_Id,@Payment_Id,@Order_Id,@Payment_Status, @Amount,@Added_On,@Student_Name,@STD,@Fees_Type,@Remark,@Mobile,@Email)", con, transaction);

            cmd.Parameters.AddWithValue("@Student_Id", Student_Id);
            cmd.Parameters.AddWithValue("@Payment_Id", Payment_Id);

            cmd.Parameters.AddWithValue("@Order_Id", Order_Id);
            cmd.Parameters.AddWithValue("@Payment_Status", Payment_Status);
            cmd.Parameters.AddWithValue("@Amount", Amount);
            cmd.Parameters.AddWithValue("@Added_On", DateTime.Now.ToString("dd/MM/yyyy hh:mm:ss tt"));


            cmd.Parameters.AddWithValue("@STD", STD);
            cmd.Parameters.AddWithValue("@Remark", Remark);
            cmd.Parameters.AddWithValue("@Student_Name", Student_Name);
            cmd.Parameters.AddWithValue("@Fees_Type", Fees_Type);
            cmd.Parameters.AddWithValue("@Mobile", Mobile);
            cmd.Parameters.AddWithValue("@Email", Email);
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








}
