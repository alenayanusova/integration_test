package com.silverrailtech.ninja;

import com.silverrailtech.ninja.entity.Account;
import com.silverrailtech.ninja.entity.Response;
import com.silverrailtech.ninja.util.TestUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Alexander Latysh
 * @version 1.0
 * @since 2015-12-02
 */
public class SRApiTest {

    private Account account;

    private String notificationToken;

    @Test
    @Ignore
    public void registerNewUsername() throws ClientProtocolException, IOException, ClassNotFoundException {
        // Given
        HttpUriRequest request = new HttpPost("http://dev.commuter-ninja.com/rest/api/uuid/register");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        String jsonFromResponse = EntityUtils.toString(response.getEntity());

        Response<Account> accountResponse = TestUtil.<Response<Account>>readResponseValue(jsonFromResponse, Account.class);
        account = accountResponse.getData();

        Assert.assertNotNull(account);
        Assert.assertTrue(StringUtils.isNotEmpty(account.getUsername()));
    }

    @Test
    public void checkLogin() throws ClientProtocolException, IOException {
        String username = "e1ab7857-7f63-4879-bfc8-69fdc0a64515";
        // Given
        HttpUriRequest request = new HttpPost("http://dev.commuter-ninja.com/rest/api/login");
        request.setHeader("Ninja-Login", username);
        request.setHeader("Content-Type", "application/json");

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);
        StatusLine statusLine = response.getStatusLine();
        notificationToken = response.getFirstHeader("Ninja-Token").getValue();

        Assert.assertNotNull(statusLine);
        Assert.assertNotNull(notificationToken);

        Assert.assertEquals(200, statusLine.getStatusCode());
        Assert.assertTrue(StringUtils.isNotEmpty(notificationToken));
    }
}
