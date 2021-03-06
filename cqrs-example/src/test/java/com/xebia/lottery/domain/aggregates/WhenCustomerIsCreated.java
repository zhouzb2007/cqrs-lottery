package com.xebia.lottery.domain.aggregates;

import org.junit.Test;

import com.xebia.cqrs.domain.VersionedId;
import com.xebia.lottery.events.CustomerBalanceChangedEvent;
import com.xebia.lottery.events.CustomerCreatedEvent;
import com.xebia.lottery.shared.Address;
import com.xebia.lottery.shared.CustomerInfo;


public class WhenCustomerIsCreated extends BddTestCase  {

    public static final VersionedId CUSTOMER_ID = VersionedId.random();
    public static final CustomerInfo CUSTOMER_INFO = new CustomerInfo("Jan Jansen", "jan@jansen.nl", new Address("Plantage Middenlaan", "20", "1018 DE", "Amsterdam", "Nederland"));

    private Customer subject;
    
    @Override
    protected void when() {
        subject = new Customer(CUSTOMER_ID, CUSTOMER_INFO, 10.0);
    }

    @Test
    public void shouldRaiseCustomerCreatedEvent() {
        assertChange(subject, new CustomerCreatedEvent(CUSTOMER_ID, CUSTOMER_INFO));
    }
    
    @Test
    public void shouldGiveCustomerInitialBalance() {
        assertChange(subject, new CustomerBalanceChangedEvent(CUSTOMER_ID, 0,10.0, 10.0));
    }
    
}
