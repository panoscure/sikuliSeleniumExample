package com.intralot.qa.automation.api.lottery.apigatewayj.ticketDetails;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import com.intralot.qa.automation.api.lottery.HeadersQueryAndFormData;
import com.intralot.qa.automation.core.lottery.apigatewayj.Authentication;
import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Properties;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.intralot.qa.automation.core.lottery.apigatewayj.Wagers.getWagerDetailsBySerialNumber;

public class TicketDetails {

    static HeadersQueryAndFormData ticket = new HeadersQueryAndFormData();

    public static String ticket_status;

    public static Map<String, String> getTicketDetails(String sn) {
        sn = sn.substring(0, 32);
        Response response = (Response) getWagerDetailsBySerialNumber(CustomProperties.getPropertyValue("swagger.apigateway.taiwan.url"),ticket.getCommonHeaders("100"),sn);
        //System.out.println("Response for Ticket Details"+test);
        try {
            JsonPath jsonPath = response.jsonPath();
            String status = jsonPath.getString("wager.dbg[0].status");
            System.out.println("Ticket Status: " + status);
            setTicketStatus(status);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setTicketStatus(String status){ticket_status=status;    }

    public static String getTicketStatus(){return(ticket_status);}


    public static void assertStatusCanceled() {
        Truth.assertThat(getTicketStatus()).isEqualTo("Canceled");
    }
    public static void assertStatusNotCanceled() {
        Truth.assertThat(getTicketStatus()).isEqualTo("Played");
    }
}