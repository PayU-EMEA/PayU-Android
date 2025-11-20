package com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.use_cases;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates.PblPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.predicates.WhiteListPredicate;
import com.payu.android.front.sdk.payment_library_payment_chooser.payment_method.internal.repository.PaymentMethodRepository;
import com.payu.android.front.sdk.payment_library_payment_methods.model.PaymentMethod;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Predicates.and;
import static com.google.common.collect.Lists.newArrayList;

//For the list of values please see http://developers.payu.com/en/overview.html#Polish_PBLs
public class RetrievePblPaymentMethods {
    private static final List<String> ALLOWED_METHODS = Arrays.asList(
            "blik", "m", "w", "o", "i", "p", "g", "gbx", "l", "ab", "bn", "wm", "wc", "bo", "bnx",
            "bs", "nstb", "plsb", "b", "t", "sgb",
            // Pay Letter
            "dpts", "dpcz", "dpt", "dpkl", "dpp", "ppf", "dppron", "dpklczk", "dpkleur", "dpklhuf",
            "dpklron", "blikbnpl",
            // Czech
            "cs", "mp", "kb", "rf", "pg", "pf", "cb", "uc", "bt", "pt", "qrcz",
            // Slovak
            "bliksk", "posta", "sporo", "tatra", "vub",  "viamo",
            // International
            "pbc", "pmb", "paypal", "pmbc", "ptrl", "pscd", "sdd", "sb", "uni",
            // PL
            "ms", "gs", "pks", "is", "os", "wms", "ws", "abs", "wys",
            // CZ
            "css", "pfs", "kbs", "airs", "cbs", "creds", "jtbs", "mons", "rbczs", "ucczs", "mps"
    );

    @NonNull
    private final PaymentMethodRepository paymentMethodRepository;

    public RetrievePblPaymentMethods(@NonNull PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @NonNull
    public LiveData<List<PaymentMethod>> getPaymentMethods() {
        return getFilteredPaymentMethods();
    }

    @NonNull
    private LiveData<List<PaymentMethod>> getFilteredPaymentMethods() {
        return Transformations.map(
                paymentMethodRepository.getPayments(),
                input -> newArrayList(Collections2.filter(input, createPredicate()))
        );
    }

    private Predicate<PaymentMethod> createPredicate() {
        return and(new WhiteListPredicate(ALLOWED_METHODS), new PblPredicate());
    }

    public void updateSelectedMethod(@NonNull String id) {
        paymentMethodRepository.updateSelectedMethod(id);
    }
}
