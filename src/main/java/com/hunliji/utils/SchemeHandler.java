package com.hunliji.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * yapi返回模型数据处理成实际接口返回数据json模型类
 */
public class SchemeHandler {
    public String parseYapiSchema(String resSchemaStr) throws ClassNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval("function parseYapiSchema(data) {\n" +
                    "    var res = JSON.parse(data)\n" +
                    "\n" +
                    "    function parseItem(item) {\n" +
                    "        switch (item.type) {\n" +
                    "            case 'object':\n" +
                    "                var obj = {}\n" +
                    "                for (var key in item.properties) {\n" +
                    "                    obj[key] = parseItem(item.properties[key])\n" +
                    "                }\n" +
                    "                return obj\n" +
                    "            case 'array':\n" +
                    "                return [parseItem(item.items)]\n" +
                    "            case 'boolean':\n" +
                    "                return true\n" +
                    "            case 'string':\n" +
                    "                return ''\n" +
                    "            case 'integer':\n" +
                    "                return 1\n" +
                    "            case 'number':\n" +
                    "                return 1\n" +
                    "            default:\n" +
                    "                return null\n" +
                    "        }\n" +
                    "    }\n" +
                    "    return JSON.stringify(parseItem(res))\n" +
                    "}");
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            String resultStr = (String) in.invokeFunction("parseYapiSchema", resSchemaStr);
            return resultStr;
        }
        return null;
    }
}
