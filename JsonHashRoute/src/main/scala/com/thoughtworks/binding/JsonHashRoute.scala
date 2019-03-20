package com.thoughtworks.binding
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.{Window, window}
import upickle.default._

import scala.scalajs.js.URIUtils.decodeURIComponent

class JsonHashRoute[PageState: Reader: Writer](state: Var[PageState], window: Window = window)
    extends HashRoute[PageState](
      state, {
        case hashText if hashText.startsWith("#") =>
          read[PageState](decodeURIComponent(hashText.substring(1)))
      },
      write(_),
      window
    )
