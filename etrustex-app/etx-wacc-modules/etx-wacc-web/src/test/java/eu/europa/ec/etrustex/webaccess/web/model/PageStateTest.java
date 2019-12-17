package eu.europa.ec.etrustex.webaccess.web.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static eu.europa.ec.etrustex.webaccess.web.model.PageState.Param;
import static org.mockito.Mockito.*;

public class PageStateTest extends eu.europa.ec.etrustex.test.support.AbstractControllerTest{

    @Test
    public void test_getValue_should_returnValuesFromRequest_when_valuesInRequestAreAvailable() throws Exception {
        Map<String, String[]> request = new HashMap<>();
        request.put(Param.SORT_DIRECTION.getName(), new String[]{"ASC"});
        request.put(Param.SUBJECT.getName(), new String[]{"10OF10"});
        request.put(Param.UNREAD_ONLY.getName(), new String[]{"true"});
        request.put(Param.VIEW.getName(), new String[]{"some"});
        request.put(Param.PAGE.getName(), new String[]{"2"});
        request.put(Param.NOTIFY_SUCCESSFUL_OPERATION.getName(), new String[]{"true"});
        request.put(Param.BACK_ACTION_DO.getName(), new String[]{"page53.do"});
        request.put(Param.BACK_VIEW.getName(), new String[]{"v13"});
        request.put(Param.MESSAGE_ID.getName(), new String[]{"98"});

        PageState pageState = new PageState();
        pageState.populate(request);

        // DO THE ACTUAL CALLS
        assertThat(pageState.getValue(Param.SORT_DIRECTION), is(equalTo("ASC")));
        assertThat(pageState.getValue(Param.SUBJECT), is(equalTo("10OF10")));
        assertThat(pageState.getValue(Param.UNREAD_ONLY), is(equalTo("true")));
        assertThat(pageState.getValue(Param.VIEW), is(equalTo("some")));
        assertThat(pageState.getValue(Param.PAGE), is(equalTo("2")));
        assertThat(pageState.getValue(Param.NOTIFY_SUCCESSFUL_OPERATION), is(equalTo("true")));
        assertThat(pageState.getValue(Param.BACK_ACTION_DO), is(equalTo("page53.do")));
        assertThat(pageState.getValue(Param.BACK_VIEW), is(equalTo("v13")));
        assertThat(pageState.getValue(Param.MESSAGE_ID), is(equalTo("98")));
        assertThat(pageState.getParams().size(), is(9));
    }

    @Test
    public void test_getValue_should_returnDefaultValues_when_valuesInRequestAreNotAvailable() throws Exception {
        PageState pageState = new PageState();

        // DO THE ACTUAL CALLS
        assertThat(pageState.getValue(Param.SORT_DIRECTION), is(equalTo("DESC")));
        assertThat(pageState.getValue(Param.SUBJECT), is(equalTo("")));
        assertThat(pageState.getValue(Param.UNREAD_ONLY), is(equalTo("false")));
        assertThat(pageState.getValue(Param.VIEW), is(equalTo("custom")));
        assertThat(pageState.getValue(Param.PAGE), is(equalTo("1")));
        assertThat(pageState.getValue(Param.NOTIFY_SUCCESSFUL_OPERATION), is(equalTo("false")));
        assertThat(pageState.getValue(Param.BACK_ACTION_DO), is(equalTo("inbox.do")));
        assertThat(pageState.getValue(Param.BACK_VIEW), is(equalTo("custom")));
        assertThat(pageState.getValue(Param.MESSAGE_ID), is(equalTo("-1")));
        assertThat(pageState.getParams().size(), is(0));
    }

    @Test
    public void test_buildUrl_should_buildUrl_when_requestValuesAreSupplied() throws Exception {
        Map<String, String[]> request = new HashMap<>();
        request.put(Param.SORT_DIRECTION.getName(), new String[]{"ASC"});
        request.put(Param.SUBJECT.getName(), new String[]{"10OF10"});
        request.put(Param.UNREAD_ONLY.getName(), new String[]{"true"});
        PageState pageState = new PageState();
        pageState.populate(request);

        // DO THE ACTUAL CALL
        String url = pageState.buildUrl(Param.SORT_DIRECTION, Param.SUBJECT, Param.UNREAD_ONLY);

        String expected = "&sd=ASC&s=10OF10&u=true";
        assertThat(url, is(equalTo(expected)));
    }

    @Test
    public void test_buildUrl_should_buildUrlFromDefaults_when_requestValuesAreNotSupplied() throws Exception {
        PageState pageState = new PageState();

        // DO THE ACTUAL CALL
        String url = pageState.buildUrl(Param.SORT_DIRECTION, Param.SUBJECT, Param.UNREAD_ONLY);

        String expected = "&sd=DESC&s=&u=false";
        assertThat(url, is(equalTo(expected)));
    }

    @Test
    public void test_getListWoSortUrl_should_buildUrlFrom() throws Exception {
        PageState spy = spy(new PageState());

        doReturn("").when(spy).buildUrl(argThat(any(Param.class)));

        // DO THE ACTUAL CALL
        spy.getListWoSortUrl();

        verify(spy).buildUrl(Param.VIEW, Param.SUBJECT, Param.UNREAD_ONLY, Param.PARTY_ID);
    }

    @Test
    public void test_getListWoPageUrl_should_buildUrlFrom() throws Exception {
        PageState spy = spy(new PageState());

        doReturn("").when(spy).buildUrl(argThat(any(Param.class)));

        // DO THE ACTUAL CALL
        spy.getListWoPageUrl();

        verify(spy).buildUrl(Param.VIEW, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY, Param.PARTY_ID, Param.STATUS);
    }

    @Test
    public void test_getListWoUnreadUrl_should_buildUrlFrom() throws Exception {
        PageState spy = spy(new PageState());

        doReturn("").when(spy).buildUrl(argThat(any(Param.class)));

        // DO THE ACTUAL CALL
        spy.getListWoUnreadUrl();

        verify(spy).buildUrl(Param.VIEW, Param.SUBJECT, Param.SORT_DIRECTION, Param.PARTY_ID);
    }

    @Test
    public void test_getBackToListUrl_should_buildUrlFrom() throws Exception {
        PageState spy = spy(new PageState());

        doReturn("").when(spy).buildUrl(argThat(any(Param.class)));

        // DO THE ACTUAL CALL
        spy.getBackToListUrl();

        verify(spy).buildUrl(Param.PAGE, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY, Param.PARTY_ID);
    }

    @Test
    public void test_getToMessageUrl_should_buildUrlFrom() throws Exception {
        PageState spy = spy(new PageState());

        doReturn("").when(spy).buildUrl(argThat(any(Param.class)));

        // DO THE ACTUAL CALL
        spy.getToMessageUrl();

        verify(spy).buildUrl(Param.BACK_ACTION_DO, Param.BACK_VIEW, Param.PAGE, Param.SUBJECT, Param.SORT_DIRECTION, Param.UNREAD_ONLY);
    }
}
