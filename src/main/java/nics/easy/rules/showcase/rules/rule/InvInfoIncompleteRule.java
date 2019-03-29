package nics.easy.rules.showcase.rules.rule;

import com.github.selwynshen.nics.rules.annotation.Action;
import com.github.selwynshen.nics.rules.annotation.Condition;
import com.github.selwynshen.nics.rules.annotation.Rule;
import lombok.extern.slf4j.Slf4j;
import nics.easy.rules.showcase.data.Invoice;
import nics.easy.rules.showcase.rules.fact.EntryCheckFacts;
import nics.easy.rules.spring.rule.AbstractRule;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 发票信息不全规则
 * @author Selwyn
 * @version $Id: InvoiceInfoNotCompleteRule.java, v 0.1 1/4/2019 8:09 PM Selwyn Exp $
 */
@Rule(name="invInfoIncompleteRule", description = "发票信息不全规则")
@Component
@Scope("prototype")
@Slf4j
public class InvInfoIncompleteRule extends AbstractRule {

    private static final String KEY_EMPTY_FIELD_NAME = "emptyFieldName";

    @Condition
    public boolean condition(EntryCheckFacts facts)
    {
        Invoice invoice = facts.getInvoice();
        if (StringUtils.isEmpty(invoice.getInvSn())) {
            facts.put(KEY_EMPTY_FIELD_NAME, "invSn");
            return true;
        } else if (StringUtils.isEmpty(invoice.getIntoDate())) {
            facts.put(KEY_EMPTY_FIELD_NAME, "intoDate");
            return true;
        } else if (StringUtils.isEmpty(invoice.getOutDate())) {
            facts.put(KEY_EMPTY_FIELD_NAME, "outDate");
            return true;
        }
        return false;
    }

    @Action
    public void action(EntryCheckFacts facts)
    {
        String fieldName = facts.get(KEY_EMPTY_FIELD_NAME);
        log.error("字段'{}'值为空", fieldName);
        facts.putEnd(true);
    }
}
