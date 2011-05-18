package org.yes.cart.service.order.impl.handler;

import org.junit.After;

import org.junit.Before;
import org.junit.Test;
import org.yes.cart.domain.entity.Customer;
import org.yes.cart.domain.entity.CustomerOrder;
import org.yes.cart.service.domain.CustomerOrderService;
import org.yes.cart.service.order.impl.OrderAssemblerImplTest;
import org.yes.cart.service.order.impl.OrderEventImpl;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public class CancelOrderEventHandlerImplTest extends  AbstractEventHandlerImplTest {

    private CustomerOrderService orderService = null;
    private CancelOrderEventHandlerImpl handler = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();


        handler = (CancelOrderEventHandlerImpl) ctx.getBean("cancelOrderEventHandler");
        orderService =  (CustomerOrderService)  ctx.getBean("customerOrderService");
    }

    @After
    public void tearDown() {
        orderService = null;
        handler = null;
        super.tearDown();
    }

    @Test
    public void testHandle() {

        final Customer customer = OrderAssemblerImplTest.createCustomer(ctx);
        assertFalse(customer.getAddress().isEmpty());

        final CustomerOrder customerOrder = orderService.createFromCart(getStdCard(ctx, customer.getEmail()), false);
        assertEquals(CustomerOrder.ORDER_STATUS_NONE, customerOrder.getOrderStatus());

        handler.handle(
                new OrderEventImpl(
                        "", //evt.payment.offline
                        customerOrder
                )
        );
        assertEquals(CustomerOrder.ORDER_STATUS_CANCELLED, customerOrder.getOrderStatus());

    }

}