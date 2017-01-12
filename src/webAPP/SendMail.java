package webAPP;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import entity.Achievement;
import entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by orient on 2016/12/11.
 */
public class SendMail{

    private static WebResource webResource;

    static
    {
        Client client=Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api",
                "key-77ed9a5a192dd6c5b90591b027c769b1"));
        webResource = client.resource("https://api.mailgun.net/v3/gocheer.donggu.me/messages");
    }

    private static class Mailing implements Runnable{
        WebResource webResource;
        User user;
        Achievement achievement;

        public Mailing(WebResource webResource, User user, Achievement achievement) {
            this.webResource = webResource;
            this.user = user;
            this.achievement = achievement;
        }

        @Override
        public void run() {
            String text_title="Dear "+user.getAlias()+"\n.";
            String text_end="Hope you make persistent efforts!\n";

            String text_content="Congratulations on obtaining the "
                    +(achievement.isHidden()?"hidden ":"")+"achievement "
                    +achievement.getName()+":\n "+achievement.getDescription()+"\n";
            String text=text_title+text_content+text_end;

            MultivaluedMapImpl formData = new MultivaluedMapImpl();
            formData.add("from", "GoCheer Achievements <postmaster@gocheer.donggu.me>");
            formData.add("to", user.getEmail());
            formData.add("subject", "New GoCheer achievement: "+achievement.getName()+"\n.");
            formData.add("text",text);
            webResource.post(ClientResponse.class,formData);
        }
    }

    static void SendCongratulations(User user, Achievement achievement){
          new Thread(new Mailing(webResource,user,achievement)).start();
    }

}
