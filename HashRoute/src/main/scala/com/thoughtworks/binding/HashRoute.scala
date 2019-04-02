package com.thoughtworks.binding

import com.thoughtworks.binding.Binding.{SingleMountPoint, Var}
import org.scalajs.dom.{window, _}

import scala.scalajs.js
import scala.scalajs.js.URIUtils.decodeURIComponent
import scala.util.control.NonFatal

/**
  * Let `state` always reflect the [[org.scalajs.dom.raw.Location.hash hash]] of the [[org.scalajs.dom.raw.Window.location location]] of the current [[org.scalajs.dom.window window]].
  */
class HashRoute[PageState](val state: Var[PageState],
                           parse: String => PageState,
                           format: PageState => String,
                           window: Window = window)
    extends SingleMountPoint[PageState](state) {

  protected def set(value: PageState): Unit = {
    window.location.hash = format(value)
  }

  private def updateState(): Unit = {
    val hashText = decodeURIComponent(window.location.hash match {
      case hashText if hashText.startsWith("#") =>
        hashText.substring(1)
      case hashText =>
        hashText
    })
    state.value = try {
      parse(hashText)
    } catch {
      case NonFatal(e) =>
        e.printStackTrace()
        return
    }
  }

  private val listener: js.Function1[HashChangeEvent, Unit] = { _: HashChangeEvent =>
    updateState()
  }

  override protected def mount(): Unit = {
    updateState()
    super.mount()
    window.addEventListener("hashchange", listener)
  }

  override protected def unmount(): Unit = {
    window.removeEventListener("hashchange", listener)
    super.unmount()
  }

}
