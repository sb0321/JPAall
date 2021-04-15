package dialect;

import org.hibernate.dialect.MariaDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyMariaDBDialect extends MariaDBDialect {

    public MyMariaDBDialect() {
        registerFunction("group_concat", new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
    }
}
