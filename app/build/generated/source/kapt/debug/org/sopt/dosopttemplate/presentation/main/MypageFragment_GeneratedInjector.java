package org.sopt.dosopttemplate.presentation.main;

import org.sopt.dosopttemplate.presentation.main.mypage.MypageFragment;

import dagger.hilt.InstallIn;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.internal.GeneratedEntryPoint;
import javax.annotation.processing.Generated;

@OriginatingElement(
    topLevelClass = MypageFragment.class
)
@GeneratedEntryPoint
@InstallIn(FragmentComponent.class)
@Generated("dagger.hilt.android.processor.internal.androidentrypoint.InjectorEntryPointGenerator")
public interface MypageFragment_GeneratedInjector {
  void injectMypageFragment(MypageFragment mypageFragment);
}
