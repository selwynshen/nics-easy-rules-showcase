package nics.easy.rules.showcase.rules.fact;

import nics.easy.rules.showcase.data.Invoice;
import nics.easy.rules.spring.facts.AdvFacts;

/**
 * @author Selwyn
 * @version $Id: EntryCheckFacts.java, v 0.1 3/29/2019 7:16 PM Selwyn Exp $
 */
public class EntryCheckFacts extends AdvFacts{

    private static final String KEY_INVOICE = "invoice";

    public void putInvoice(Invoice invoice)
    {
        this.put(KEY_INVOICE, invoice);
    }

    public Invoice getInvoice()
    {
        return this.get(KEY_INVOICE);
    }
}
