package gg.lol.android

import androidx.compose.ui.graphics.vector.ImageVector
import gg.lol.android.iconpack.*
import kotlin.collections.List as ____KtList

public object IconPack

private var __Icons: ____KtList<ImageVector>? = null

public val IconPack.Icons: ____KtList<ImageVector>
  get() {
    if (__Icons != null) {
      return __Icons!!
    }
    __Icons= listOf(Champion, Community, Esports, Home, Setting)
    return __Icons!!
  }
