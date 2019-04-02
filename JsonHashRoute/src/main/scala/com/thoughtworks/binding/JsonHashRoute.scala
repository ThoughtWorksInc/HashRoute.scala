package com.thoughtworks.binding
import com.thoughtworks.binding.Binding.Var
import org.scalajs.dom.{Window, window}
import upickle.default._

class JsonHashRoute[PageState: Reader: Writer](state: Var[PageState], window: Window = window)
    extends HashRoute[PageState](state, read[PageState](_), write(_), window)
