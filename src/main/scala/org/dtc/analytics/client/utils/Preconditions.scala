package org.dtc.analytics.client.utils

import com.google.common.annotations.VisibleForTesting
import com.sun.istack.internal.Nullable

import scala.util.control.Breaks._


/**
  * Created on 2019-06-18
  *
  * @author :hao.li
  */
object Preconditions {
  def checkNotNullOrEmpty(reference: String): String = {
    com.google.common.base.Preconditions.checkNotNull(reference)
    if (reference.isEmpty)
      throw new IllegalArgumentException
    reference
  }

  def checkNotNullOrEmpty(reference: String, @Nullable errorMessageTemplate: String, @Nullable errorMessageArgs: Any*): String = {
    com.google.common.base.Preconditions.checkNotNull(reference, errorMessageTemplate, errorMessageArgs)
    if (reference.isEmpty) throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs))
    reference
  }

  @VisibleForTesting
  private[utils] def format(template: String, @Nullable args: Any*): String = {
   var template_value = String.valueOf(template) // null -> "null"

    // start substituting the arguments into the '%s' placeholders
    val builder = new StringBuilder(template_value.length + 16 * args.length)
    var templateStart = 0
    var i = 0
    while ( {
      i < args.length
    }) {
      val placeholderStart = template_value.indexOf("%s", templateStart)
      if (placeholderStart == -1)
        break //todo: break is not supported
      builder.append(template_value.substring(templateStart, placeholderStart))
      builder.append(args({
        i += 1; i - 1
      }))
      templateStart = placeholderStart + 2
    }
    builder.append(template_value.substring(templateStart))
    // if we run out of placeholders, append the extra args in square braces
    if (i < args.length) {
      builder.append(" [")
      builder.append(args({
        i += 1; i - 1
      }))
      while ( {
        i < args.length
      }) {
        builder.append(", ")
        builder.append(args({
          i += 1; i - 1
        }))
      }
      builder.append(']')
    }
    return builder.toString
  }

}
