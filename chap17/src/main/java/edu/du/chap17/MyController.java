package edu.du.chap17;

import edu.du.chap17.model.Article;
import edu.du.chap17.model.ArticleListModel;
import edu.du.chap17.service.ArticleNotFoundException;
import edu.du.chap17.service.ListArticleService;
import edu.du.chap17.service.ReadArticleService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    ListArticleService listSerivce;

    @Autowired
    ReadArticleService readSerivce;

    @GetMapping("/")
    public String index() {
        return "redirect:list";
    }

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        String pageNumberString = request.getParameter("p");
        int pageNumber = 1;
        if (pageNumberString != null && pageNumberString.length() > 0) {
            pageNumber = Integer.parseInt(pageNumberString);
        }
        ArticleListModel articleListModel = listSerivce.getArticleList(pageNumber);
        model.addAttribute("listModel", articleListModel);

        if (articleListModel.getTotalPageCount() > 0) {
            int beginPageNumber =
                    (articleListModel.getRequestPage() - 1) / 10 * 10 + 1;
            int endPageNumber = beginPageNumber + 9;
            if (endPageNumber > articleListModel.getTotalPageCount()) {
                endPageNumber = articleListModel.getTotalPageCount();
            }
            model.addAttribute("beginPage", beginPageNumber);
            model.addAttribute("endPage", endPageNumber);
        }
        return "list_view";
    }

    @GetMapping("/read")
    public String read(Model model, HttpServletRequest request) {
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String viewPage = null;
        try {
            Article article = readSerivce.readArticle(articleId);
            model.addAttribute("article", article);
            viewPage = "/read_view.jsp";
        } catch(ArticleNotFoundException ex) {
            viewPage = "/article_not_found.jsp";
        }
        return "read_view";
    }
}
