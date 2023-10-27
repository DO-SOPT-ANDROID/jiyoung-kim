package org.sopt.dosopttemplate.presentation.main;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import org.sopt.dosopttemplate.domain.repository.AuthRepository;
import org.sopt.dosopttemplate.presentation.main.mypage.MypageViewModel;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class MainViewModel_Factory implements Factory<MypageViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public MainViewModel_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public MypageViewModel get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static MainViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider) {
    return new MainViewModel_Factory(authRepositoryProvider);
  }

  public static MypageViewModel newInstance(AuthRepository authRepository) {
    return new MypageViewModel(authRepository);
  }
}
