package akkaviz.frontend.components

import org.scalajs.dom.{Element, Event}
import rx.Var

class UnconnectedOnOff(status: Var[Boolean]) extends OnOffWithLabel with Component {
  lbl.innerHTML = "Show actors without any connections on graph"
  inp.onchange = { e: Event =>
    status() = inp.checked
  }

  override def attach(parent: Element): Unit = parent.appendChild(stateBtn.render)
}
