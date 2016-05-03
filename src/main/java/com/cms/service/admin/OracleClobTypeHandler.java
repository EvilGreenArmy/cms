package com.cms.service.admin;

import oracle.sql.CLOB;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.io.Writer;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Evan on 2016/5/2.
 */
public class OracleClobTypeHandler implements TypeHandler<Object> {
    public Object valueOf(String param) {
        return null;
    }

    @Override
    public Object getResult(ResultSet arg0, String arg1) throws SQLException {
        CLOB clob = (CLOB) arg0.getClob(arg1);
        return (clob == null || clob.length() == 0) ? null : clob.getSubString((long) 1, (int) clob.length());
    }

    @Override
    public Object getResult(ResultSet arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public Object getResult(CallableStatement arg0, int arg1) throws SQLException {
        return null;
    }

    @Override
    public void setParameter(PreparedStatement arg0, int arg1, Object arg2, JdbcType arg3) throws SQLException {
        try {
            CLOB clob = CLOB.empty_lob();
            //clob.setString(1, (String) arg2);
            Method methodToInvoke = clob.getClass().getMethod("getCharacterOutputStream", (Class[]) null);
            Writer writer = (Writer) methodToInvoke.invoke(clob, (Object[]) null);
            writer.write((String) arg2);
            writer.close();
            arg0.setClob(arg1, clob);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String htmlSpecialChars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
}
