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
            "blik", "m", "w", "wc", "nstb", "sgb", "plsb", "o", "i", "p", "g", "gbx", "l", "as",
            "u", "ab", "ps", "wm", "wd", "bo", "bnx", "orx", "bs", "t", "cs", "mp", "kb", "rf",
            "pg", "pf", "era", "cb", "uc", "bt", "pt", "uni", "sporo", "tatra", "vub", "posta",
            "viamo", "sb", "it", "gp", "sdd", "pid", "pbc", "pmb", "paypal", "pmbc", "ptrl", "ucro",
            "dpts", "dpcz", "dpt", "dpkl", "dpp", "dppron", "dpklczk", "dpkleur", "dpklhuf",
            "dpklron", "ppf", "qrcz",
            // PSD2PL
            "ms", "gs", "pks", "is", "os", "wms", "ws", "abs",
            // PSD2CZ
            "css", "pfs", "kbs", "airs", "cbs"
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
