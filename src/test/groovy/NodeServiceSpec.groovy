import com.hermanlee.tradesquare.domain.Node
import com.hermanlee.tradesquare.services.NodeService
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import java.util.stream.Stream

@Stepwise
class NodeServiceSpec extends Specification {

    @Shared NodeService nodeService = new NodeService()
    @Shared Node head

    def "test merge and sort"() {
        setup:
        int[] numbers1 = nodeService.generateSequentialIntArray(1, 6) // 1,2,3,4,5,6
        int[] numbers2 = nodeService.generateSequentialIntArray(6, 9) // 6,7,8,9

        Node head1 = Node.fromArray(numbers1)
        Node head2 = Node.fromArray(numbers2)

        int[] combined = [1,2,3,4,5,6,6,7,8,9]
        Arrays.sort(combined)

        when: "merge and sort the two"
        def result = nodeService.mergeAndSortLinkedList(head1, head2)
        head = result

        then: "should return a combined and sorted list"
        result.size == combined.length
        Node cur = result
        for (int n: combined) {
            cur.val == n
            cur = cur.next
        }
    }

    def "test save and load"() {
        when: "saving to a file"
        nodeService.saveLinkedListInJson(head, "test.json")
        def result = nodeService.loadLinkedListInJson("test.json")

        then: "should get the same linked list back"
        result.size == head.size
        Node cur1 = result
        Node cur2 = head
        while (cur1 != null) {
            cur1.val == cur2.val
            cur1 = cur1.next
            cur2 = cur2.next
        }
    }

}
