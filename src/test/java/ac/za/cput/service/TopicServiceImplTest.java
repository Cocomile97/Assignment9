package ac.za.cput.service;

import ac.za.cput.Repository.impl.CourseRepositoryImpl;
import ac.za.cput.Repository.impl.TopicRepositoryImpl;
import ac.za.cput.domain.Course;
import ac.za.cput.domain.Topic;
import ac.za.cput.factory.CourseFactory;
import ac.za.cput.factory.TopicFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TopicServiceImplTest {

    private TopicRepositoryImpl repository;
    private Topic topic;

    private Topic getSaved(){
        return this.repository.getAll().iterator().next();
    }

    @Before
    public void setUp() throws Exception {
        this.repository = TopicRepositoryImpl.getRepository();
        this.topic = TopicFactory.getTopic("");
    }

    @Test
    public void a_create() {
        Topic created = this.repository.create(this.topic);
        System.out.println("In create, created = " + created);
        Assert.assertNotNull(created);
        Assert.assertSame(created, this.topic);
    }

    @Test
    public void c_update() {
        String newTopicName = "System Designing";
        Topic updated = new Topic.Builder().copy(getSaved()).topicName(newTopicName).build();
        System.out.println("In update, updated = " + updated);
        this.repository.update(updated);
        Assert.assertSame(newTopicName, updated.getTopicName());
    }

    @Test
    public void e_delete() {
        Topic saved = getSaved();
        this.repository.delete(saved.getTopicName());
        d_getAll();
    }

    @Test
    public void b_read() {
        Topic saved = getSaved();
        Topic read = this.repository.read(saved.getTopicName());
        System.out.println("In read, read = "+ read);
        Assert.assertSame(read, saved);
    }

    @Test
    public void d_getAll() {
        Set<Topic> topics = this.repository.getAll();
        System.out.println("In getall, all = " + topics);
    }
}