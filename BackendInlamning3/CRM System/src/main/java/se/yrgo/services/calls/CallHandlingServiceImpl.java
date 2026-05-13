package se.yrgo.services.calls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Action;
import se.yrgo.domain.Call;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;
import se.yrgo.services.diary.DiaryManagementService;

import java.util.Collection;

@Service
@Transactional
public class CallHandlingServiceImpl implements CallHandlingService {
    private CustomerManagementService cms;
    private DiaryManagementService dms;

    @Autowired
    public CallHandlingServiceImpl(CustomerManagementService cms,
                                   DiaryManagementService dms) {
        this.cms = cms;
        this.dms = dms;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        cms.recordCall(customerId, newCall);

        for(Action action : actions) {
            dms.recordAction(action);
        }

    }
}
