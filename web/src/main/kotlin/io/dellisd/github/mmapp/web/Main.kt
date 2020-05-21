package io.dellisd.github.mmapp.web

import react.RProps
import react.child
import react.dom.h1
import react.dom.render
import react.functionalComponent
import kotlin.browser.document

val test = functionalComponent<RProps> {
    h1 {
        +"Hello World!"
    }
}

fun main() {
    render(document.getElementById("root")) {
        child(test)
    }
}