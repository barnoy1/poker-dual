package com.game.pokerdual.ui.login;

import com.firebase.authwrapper.manager.ProviderManager;
import com.firebase.authwrapper.providers.common.callbacks.AuthenticationListener;
import com.firebase.authwrapper.providers.common.enums.ProviderType;
import com.firebase.authwrapper.providers.types.IProvider;
import com.game.pokerdual.R;
import com.game.pokerdual.data.DataManager;
import com.game.pokerdual.ui.base.BasePresenter;
import com.game.pokerdual.utils.Logger;
import com.game.pokerdual.utils.AssetLoader.AssetLoadManager;
import com.game.pokerdual.utils.CommonUtils;
import com.game.pokerdual.utils.SchedulerProvider.SchedulerProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ron on 9/30/2018.
 */

public class LoginPresenter<V extends LoginMvpView> extends BasePresenter<V>
        implements LoginMvpPresenter<V> {

    @Inject
    public ProviderManager mProviderManager;

    @Inject
    public LoginPresenter(DataManager dataManager,
                          SchedulerProvider schedulerProvider,
                          CompositeDisposable compositeDisposable,
                          AssetLoadManager assetLoadManager) {

        super(dataManager, schedulerProvider, compositeDisposable, assetLoadManager);

    }

    @Override
    public void onServerLoginClick(String email, String password) {

        //validate email and password
        if (email == null || email.isEmpty()) {
            getMvpView().onError(R.string.empty_email);
            return;
        }
        if (!CommonUtils.isEmailValid(email)) {
            getMvpView().onError(R.string.email_invalid_adderss);
            return;
        }
        if (password == null || password.isEmpty()) {
            getMvpView().onError(R.string.empty_password);
            return;
        }

        // use auth library
        if (mProviderManager.IsConfigured())
        {
            IProvider provider = mProviderManager
                    .getProvider(ProviderType.MAIL);

            if (!CommonUtils.isEmailValid(email)){
                int stringId = R.string.email_invalid_adderss;
                getMvpView().showSnackBarMessage(stringId);
                Logger.i( getMvpView().getStringByID(stringId) );
                return;
            }

            provider.SignIn(email, password, new
                    AuthenticationListener.Email() {

                        @Override
                        public void OnNewUserCreated(FirebaseUser user) {
                            provider.SendEmailVerification(user);
                        }

                        @Override
                        public void OnVerficationMailSent() {
                            int stringId = R.string.email_sent_verifcation_mail;
                            getMvpView().showSnackBarMessage(stringId);
                            Logger.i( getMvpView().getStringByID(stringId) );
                        }

                        @Override
                        public void OnAccountAlreadyExistsError() {
                            int stringId = R.string.email_account_already_exists_exception;
                            getMvpView().showSnackBarMessage(stringId);
                            Logger.i( getMvpView().getStringByID(stringId) );
                        }

                        @Override
                        public void OnEmailSignIn(FirebaseUser firebaseUser) {

                            if (firebaseUser.isEmailVerified()){

                                int stringId = R.string.email_authentication_completed;
                                getMvpView().showSnackBarMessage(stringId);
                                Logger.i( getMvpView().getStringByID(stringId)  );

                                getMvpView().OnAuthenticationComplete(firebaseUser);
                                return;
                            }
                            String message = String.format("User %s is not authorized, please complete the " +
                                    "verification from sent to your address", firebaseUser.getDisplayName() );
                            getMvpView().showSnackBarMessage(message);
                            Logger.i(message);
                        }

                    });
        }
    }

    @Override
    public void onGoogleLoginClick() {
        // use auth library
        if (mProviderManager.IsConfigured())
        {
            IProvider provider = mProviderManager
                    .getProvider(ProviderType.GOOGLE);
            try {
                provider.SignIn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFacebookLoginClick() {
        // use auth library
        if (mProviderManager.IsConfigured())
        {
            IProvider provider = mProviderManager
                    .getProvider(ProviderType.FACEBOOK);
            try {
                provider.SignIn();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean verifyUserDisplayName(@NotNull FirebaseUser firebaseUser) {

        boolean isNullDisplayName = firebaseUser.getDisplayName() == null;
        if (isNullDisplayName)
            return false;

        boolean isEmptyDisplayName = Objects.requireNonNull(firebaseUser.getDisplayName()).isEmpty();
        if (isEmptyDisplayName)
            return false;

        boolean isAnonymousDisplayName = firebaseUser.getDisplayName().equals
                ( getMvpView().getStringByID(R.string.anonymous_user) );
        if (isAnonymousDisplayName)
            return false;

        return true;
    }

    @Override
    public void UpdateUserDisplayName(FirebaseUser firebaseUser, @NotNull String dialogBoxInputDisplayName) {

        String displayName =  getMvpView().getStringByID(R.string.anonymous_user);
        if (!dialogBoxInputDisplayName.isEmpty())
            displayName = dialogBoxInputDisplayName;

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName( displayName )
                .build();

        firebaseUser.updateProfile(profileUpdates);

    }
}
