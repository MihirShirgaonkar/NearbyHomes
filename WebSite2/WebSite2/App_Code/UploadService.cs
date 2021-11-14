using System;
using System.Web;
using System.Web.Services;
using System.IO;
using System.Web;
using System.Collections;
using System.Web.Services.Protocols;
using System.ComponentModel;
using System.IO;
using System.Collections.Generic;
using System.Linq;
using System.Data.SqlClient;
using System.Data;
using System.Web.Script.Serialization;

/// <summary>
/// Summary description for WebServiceCS
/// </summary>
[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]
public class UploadService : System.Web.Services.WebService {

    public UploadService()
    {
        
    }

    [WebMethod]
    public void UploadFile2(byte[] f, string fileName)
    {
        string arr;
        try
        {
         
            MemoryStream ms = new MemoryStream(f);
    
            FileStream fs = new FileStream(System.Web.Hosting.HostingEnvironment.MapPath
                        ("~/Uploads/") + fileName, FileMode.Create);
          
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
}
