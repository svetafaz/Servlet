package com.example.newservlet.config;
import com.example.newservlet.mapper.OrderMapper;
import com.example.newservlet.mapper.impl.OrderMapperImpl;
import com.example.newservlet.repository.*;
import com.example.newservlet.repository.impl.*;
import com.example.newservlet.service.*;
import com.example.newservlet.service.impl.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.example.newservlet.mapper.BookMapper;
import com.example.newservlet.mapper.impl.BookMapperImpl;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.newservlet.utils.PropertyReader;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import com.example.newservlet.mapper.CategoryMapper;
import com.example.newservlet.mapper.ReaderMapper;
import com.example.newservlet.mapper.impl.CategoryMapperImpl;
import com.example.newservlet.mapper.impl.ReaderMapperImpl;

import java.util.List;

@Slf4j
@WebListener
public class MainContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        DataSource dataSource = dataSource();
        context.setAttribute("dataSource", dataSource);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        context.setAttribute("jdbcTemplate", jdbcTemplate);

        BookMapper bookMapper = new BookMapperImpl();
        context.setAttribute("bookMapper", bookMapper);

        CategoryMapper categoryMapper = new CategoryMapperImpl();
        context.setAttribute("categoryMapper", categoryMapper);

        ReaderMapper readerMapper = new ReaderMapperImpl();
        context.setAttribute("readerMapper", readerMapper);

        OrderMapper orderMapper = new OrderMapperImpl();
        context.setAttribute("orderMapper", orderMapper);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl(jdbcTemplate,categoryMapper);
        context.setAttribute("categoryRepository", categoryRepository);

        SelectedRepository selectedRepository = new SelectedRepositoryImpl(jdbcTemplate, bookMapper, categoryRepository);
        context.setAttribute("selectedRepository", selectedRepository);


        BookRepository bookRepository = new BookRepositoryImpl(jdbcTemplate,categoryRepository,bookMapper,selectedRepository);
        context.setAttribute("bookRepository", bookRepository);

        ReaderRepository readerRepository = new ReaderRepositoryImpl(jdbcTemplate,readerMapper);
        context.setAttribute("readerRepository", readerRepository);

        OrdersRepository ordersRepository = new OrdersRepositoryImpl(jdbcTemplate, orderMapper);

        CategoryService categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);
        context.setAttribute("categoryService", categoryService);

        BookService bookService = new BookServiceImpl(bookRepository, bookMapper);
        context.setAttribute("bookService", bookService);

        ReaderService readerService = new ReaderServiceImpl(readerRepository, readerMapper);
        context.setAttribute("readerService", readerService);

        SelectedService selectedService = new SelectedServiceImpl(selectedRepository, bookMapper);
        context.setAttribute("selectedService", selectedService);

        OrdersService ordersService = new OrderServiceImpl(ordersRepository);
        context.setAttribute("ordersService", ordersService);

        List<String> PROTECTED_URIS = List.of(PropertyReader.getProperty("PROTECTED_URIS").split(","));
        context.setAttribute("PROTECTED_URIS", PROTECTED_URIS);
        List<String> PROTECTED_ADMIN_URIS = List.of(PropertyReader.getProperty("PROTECTED_ADMIN_URIS").split(","));
        context.setAttribute("PROTECTED_ADMIN_URIS", PROTECTED_ADMIN_URIS);
        List<String> NOTAUTH_URIS = List.of(PropertyReader.getProperty("NOTAUTH_URIS").split(","));
        context.setAttribute("NOTAUTH_URIS", NOTAUTH_URIS);

        String PROTECTED_REDIRECT = PropertyReader.getProperty("PROTECTED_REDIRECT");
        context.setAttribute("PROTECTED_REDIRECT", PROTECTED_REDIRECT);
        String PROTECTED_ADMIN_REDIRECT = PropertyReader.getProperty("PROTECTED_ADMIN_REDIRECT");
        context.setAttribute("PROTECTED_ADMIN_REDIRECT", PROTECTED_ADMIN_REDIRECT);
        String NOTAUTH_REDIRECT = PropertyReader.getProperty("NOTAUTH_REDIRECT");
        context.setAttribute("NOTAUTH_REDIRECT", NOTAUTH_REDIRECT);

        String AUTHORIZATION = PropertyReader.getProperty("AUTHORIZATION");
        context.setAttribute("AUTHORIZATION", AUTHORIZATION);

        String IS_ADMIN = PropertyReader.getProperty("IS_ADMIN");
        context.setAttribute("IS_ADMIN", IS_ADMIN);

        String SECRET_KEY = PropertyReader.getProperty("SECRET_KEY");
        context.setAttribute("SECRET_KEY", SECRET_KEY);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("_=_=_=_=_=_=_=_=_=_=-CONTEXT DESTROYED-==_=_=_=_=_=");
    }

    private DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(PropertyReader.getProperty("DB_URL"));
        dataSource.setUser(PropertyReader.getProperty("DB_READER"));
        dataSource.setPassword(PropertyReader.getProperty("DB_PASSWORD"));
        return dataSource;
    }

}








