package co.smartreceipts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import co.smartreceipts.android.SmartReceiptsApplication;
import co.smartreceipts.android.model.Columns;
import co.smartreceipts.android.model.Columns.Column;
import co.smartreceipts.android.model.PaymentMethod;
import co.smartreceipts.android.persistence.DatabaseHelper;

@Config(emulateSdk = 18) 
@RunWith(RobolectricTestRunner.class)
public class PaymentMethodsDBTest {

	private static final String METHOD = "method";
	private static final String METHOD2 = "method2";
	
	private SmartReceiptsApplication mApp;
	private DatabaseHelper mDB;
	
	@Before
	public void setup() {
		mApp = (SmartReceiptsApplication) Robolectric.application;
		mDB = mApp.getPersistenceManager().getDatabase();
	}
	
	@After
	public void tearDown() {
		mDB.close();
		mDB = null;
		mApp = null;
	}
	
	@Test
	public void getEmptyList() {
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		assertNotNull(paymentMethods);
		assertEquals(0, paymentMethods.size());
	}
	
	@Test
	public void getEmptyCachedList() {
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		final List<PaymentMethod> cachedPaymentMethods = mDB.getPaymentMethods();
		assertEquals(cachedPaymentMethods, paymentMethods);
	}
	
	@Test
	public void insertPaymentMethod() {
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertNotNull(paymentMethod);
		assertEquals(METHOD, paymentMethod.getMethod());
	}
	
	@Test
	public void insertPaymentMethodThenGet() {
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertNotNull(paymentMethod);
		assertEquals(METHOD, paymentMethod.getMethod());
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		assertEquals(1, paymentMethods.size());
		assertEquals(paymentMethod, paymentMethods.get(0));
	}
	
	@Test
	public void getThenInsertPaymentMethod() {
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertNotNull(paymentMethod);
		assertEquals(METHOD, paymentMethod.getMethod());
		assertEquals(1, paymentMethods.size());
		assertEquals(paymentMethod, paymentMethods.get(0));
	}
	
	@Test
	public void updatePaymentMethod() {
		final PaymentMethod oldPaymentMethod = mDB.insertPaymentMethod(METHOD);
		final PaymentMethod updatedPaymentMethod = mDB.updatePaymentMethod(oldPaymentMethod, METHOD2);
		assertNotNull(updatedPaymentMethod);
		assertEquals(METHOD2, updatedPaymentMethod.getMethod());
		assertNotSame(oldPaymentMethod, updatedPaymentMethod);
	}
	
	@Test
	public void updatePaymentMethodThenGet() {
		final PaymentMethod oldPaymentMethod = mDB.insertPaymentMethod(METHOD);
		final PaymentMethod updatedPaymentMethod = mDB.updatePaymentMethod(oldPaymentMethod, METHOD2);
		assertNotNull(updatedPaymentMethod);
		assertEquals(METHOD2, updatedPaymentMethod.getMethod());
		assertNotSame(oldPaymentMethod, updatedPaymentMethod);
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		assertEquals(1, paymentMethods.size());
		assertEquals(updatedPaymentMethod, paymentMethods.get(0));
	}
	
	@Test
	public void getThenUpdatePaymentMethod() {
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		final PaymentMethod oldPaymentMethod = mDB.insertPaymentMethod(METHOD);
		final PaymentMethod updatedPaymentMethod = mDB.updatePaymentMethod(oldPaymentMethod, METHOD2);
		assertNotNull(updatedPaymentMethod);
		assertEquals(METHOD2, updatedPaymentMethod.getMethod());
		assertNotSame(oldPaymentMethod, updatedPaymentMethod);
		assertEquals(1, paymentMethods.size());
		assertEquals(updatedPaymentMethod, paymentMethods.get(0));
	}
	
	@Test
	public void deletePaymentMethod() {
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertTrue(mDB.deletePaymenthMethod(paymentMethod));
	}
	
	@Test
	public void deletePaymentMethodThenGet() {
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertTrue(mDB.deletePaymenthMethod(paymentMethod));
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		assertFalse(paymentMethods.contains(paymentMethod));
	}
	
	@Test
	public void getThenDeletePaymentMethod() {
		final List<PaymentMethod> paymentMethods = mDB.getPaymentMethods();
		final PaymentMethod paymentMethod = mDB.insertPaymentMethod(METHOD);
		assertTrue(mDB.deletePaymenthMethod(paymentMethod));
		assertFalse(paymentMethods.contains(paymentMethod));
	}
	
}
