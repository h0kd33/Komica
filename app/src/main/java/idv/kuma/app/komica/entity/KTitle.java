package idv.kuma.app.komica.entity;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by TakumaLee on 2016/12/20.
 */

public class KTitle extends KPost {
    private List<KReply> replyList;
    /**
     * Main: http://2cat.or.tl/~tedc21thc/live/index.html
     * post link: http://2cat.or.tl/~tedc21thc/live/pixmicat.php?res=1122212
     *
     * http://2cat.or.tl/~tedc21thc/new/pixmicat.php?res=2212840
     * <a href="pixmicat.php?res=2212840&amp;page_num=all#r2212840" class="qlink">No.2212840</a>&nbsp;[
     * <a href="pixmicat.php?res=2212840">返信</a>]
     * */
    private String detailLink;

    /**
     * <span class="warn_txt2">レス 11 件省略。全て読むには返信ボタンを押してください。</span>
     * */
    protected String warnText;

    public KTitle() {
    }

    public KTitle(Element element, String domainUrl) {
        super(element, "threadpost", domainUrl);
        String newUrl = domainUrl.substring(0, domainUrl.lastIndexOf("/") + 1);
        Elements detailLinkElements = element.getElementsContainingOwnText("返信");
        if (detailLinkElements == null || detailLinkElements.size() == 0) {
            detailLinkElements = element.getElementsContainingOwnText("回應");
        }
        String hrefUrl = detailLinkElements.attr("href");
        setDetailLink(newUrl + hrefUrl);// + "&page_num=0"
        setWarnText(element.getElementsByClass("warn_txt2").text());
    }

    public List<KReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<KReply> replyList) {
        this.replyList = replyList;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public String getWarnText() {
        return warnText;
    }

    public void setWarnText(String warnText) {
        this.warnText = warnText;
    }
}
