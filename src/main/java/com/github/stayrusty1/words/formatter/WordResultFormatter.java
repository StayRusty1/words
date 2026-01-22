package com.github.stayrusty1.words.formatter;

import java.util.HashMap;
import java.util.List;

public interface WordResultFormatter {

    String format(HashMap<String, List<List<String>>> data);

}
