package eu.europa.ec.etrustex.services.converter;

import eu.europa.ec.etrustex.services.model.RestParties;
import eu.europa.ec.etrustex.services.model.RestParty;
import eu.europa.ec.etrustex.test.support.AbstractTest;
import eu.europa.ec.etrustex.webaccess.model.Party;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RestPartyConverterTest extends AbstractTest {

    @Test
    public void test_convertToRestParties_nullParties_should_return_empty_list() {
        //ACTUAL CALL
        RestParties result = RestPartyConverter.getInstance().convertToRestParties(null);

        assertThat(result.getPartyList().size(), is(0));
    }

    @Test
    public void test_convertToRestParties_emptyParties_should_return_empty_list() {
        List<Party> parties = new ArrayList<>();

        //ACTUAL CALL
        RestParties result = RestPartyConverter.getInstance().convertToRestParties(parties);

        assertThat(result.getPartyList().size(), is(0));
    }

    @Test
    public void test_convertToRestParties_should_return_restParties() {
        List<Party> parties = new ArrayList<>();

        Party party1 = new Party();
        party1.setId(1L);
        party1.setDisplayName("Party1");
        parties.add(party1);

        Party party2 = new Party();
        party2.setId(2L);
        party2.setDisplayName("Party2");
        parties.add(party2);

        RestParty restParty1 = new RestParty();
        restParty1.setId("1");
        restParty1.setName("Party1");

        RestParty restParty2 = new RestParty();
        restParty2.setId("2");
        restParty2.setName("Party2");

        //ACTUAL CALL
        RestParties result = RestPartyConverter.getInstance().convertToRestParties(parties);

        assertThat(result.getPartyList().size(), is(2));
        assertThat(result.getPartyList().get(0), is(restParty1));
        assertThat(result.getPartyList().get(1), is(restParty2));
    }

}
