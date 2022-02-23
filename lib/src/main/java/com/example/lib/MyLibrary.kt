package com.example.lib

class MyLibrary {

    private fun prc(ch: Char): Int {
        if (ch == '*' || ch == '/') {
            return 3
        } else if (ch == '+' || ch == '-') {
            return 2
        }
        return 0
    }

    fun cal(s: String): String? {
        var s = s
        var num1 = 0f
        var num2 = 0f
        var result = 0f
        var d = 0
        var f = 0
        var flag = true
        while (flag) {
            flag = false
            var index = 0
            var temppri = 0
            for (i in s.indices) {
                if (s[i] in '0'..'9' || s[i] == '.' || s[i] == '-' && i == 0) {
                    continue
                }
                if (prc(s[i]) > temppri) {
                    index = i
                    temppri = prc(s[i])
                }
            }
            var temp = ""
            var temp1 = ""
            var temp2 = ""
            run {
                var k = index - 1
                while (k >= 0 && (s[k] in '0'..'9' || s[k] == '.' || s[k] == '-' && k == 0)) {
                    temp += s[k]
                    d = k
                    k--
                }
            }
            for (k in temp.length - 1 downTo 0) {
                temp1 += temp[k]
            }
            run {
                var k = index + 1
                while (k < s.length && (s[k] in '0'..'9' || s[k] == '.' || s[k] == '-' && k == 0)) {
                    temp2 += s[k]
                    f = k
                    k++
                }
            }
            try {
                num1 = temp1.toFloat()
                num2 = temp2.toFloat()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            when (s[index]) {
                '+' -> result = num1 + num2
                '-' -> result = num1 - num2
                '*' -> result = num1 * num2
                '/' -> {
                    if (num2 == 0f) break
                    result = num1 / num2
                }
            }
            if (d != 0 || f != 0) {
                var res = result.toString()
                if (res.substring(res.indexOf(".") + 1).equals(0))
                    res = res.substring(0, res.indexOf("."))
                s = s.substring(0, d) + res + s.substring(f + 1, s.length)
            } else {
                break
            }
            for (k in s.indices) {
                if (s[k] == '-' && k == 0) {
                    flag = false
                }
                if (s[k] == '+' || s[k] == '-' || s[k] == '*' || s[k] == '/') {
                    flag = true
                }
            }
        }
        return s
    }
}