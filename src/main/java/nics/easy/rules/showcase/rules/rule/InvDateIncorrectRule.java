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

import java.time.LocalDate;
import java.util.Date;

/**
 * 发票日期错误规则
 * @author Selwyn
 * @version $Id: InvDateIncorrectRule.java, v 0.1 1/22/2019 8:53 PM Selwyn Exp $
 */
@Rule(name="invDateIncorrectRule", description = "发票日期错误规则")
@Component
@Scope("prototype")
@Slf4j
public class InvDateIncorrectRule extends AbstractRule{

    @Condition
    public boolean condition(EntryCheckFacts facts)
    {
        Invoice invoice = facts.getInvoice();
        LocalDate intoDate = LocalDate.parse(invoice.getIntoDate());
        LocalDate outDate = LocalDate.parse(invoice.getOutDate());
        boolean flag = intoDate.compareTo(outDate) > 0;
        return flag;
    }

    @Action
    public void action(EntryCheckFacts facts)
    {
        log.error("intoDate必须早于或等于outDate");
    }
}
