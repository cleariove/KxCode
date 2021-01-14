package com.kaixue.manage_course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kaixue.domain.course.CourseBase;
import com.kaixue.domain.course.TeachPlan;
import com.kaixue.domain.course.ext.CourseInfo;
import com.kaixue.domain.course.ext.TeachPlanNode;
import com.kaixue.domain.course.request.QueryCourseRequest;
import com.kaixue.domain.course.response.CourseCode;
import com.kaixue.domain.course.response.CourseResponse;
import com.kaixue.domain.course.response.TeachPlanNodeResponse;
import com.kaixue.domain.course.response.TeachPlanResponse;
import com.kaixue.manage_course.dao.CourseBaseMapper;
import com.kaixue.manage_course.dao.CourseBaseRepository;
import com.kaixue.manage_course.dao.TeachPlanMapper;
import com.kaixue.manage_course.dao.TeachPlanRepository;
import com.kaixue.model.exception.ExceptionCast;
import com.kaixue.model.response.CommonCode;
import com.kaixue.model.response.QueryResponseResult;
import com.kaixue.model.response.QueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService
{
    @Autowired
    TeachPlanMapper teachPlanMapper;

    @Autowired
    TeachPlanRepository teachPlanRepository;

    @Autowired
    CourseBaseRepository courseBaseRepository;

    @Autowired
    CourseBaseMapper courseBaseMapper;

    public CourseResponse addCourse(CourseBase courseBase)
    {
        if (courseBase == null
                || StringUtils.isEmpty(courseBase.getName())
                || StringUtils.isEmpty(courseBase.getMt())
                || StringUtils.isEmpty(courseBase.getSt())
                || StringUtils.isEmpty(courseBase.getLevel())
                || StringUtils.isEmpty(courseBase.getStudyModel()))
            ExceptionCast.cast(CommonCode.INVALID_PARAM);

        if (StringUtils.isNotEmpty(courseBase.getId()))
            courseBase.setId(null);
        courseBase = courseBaseRepository.save(courseBase);

        return new CourseResponse(CommonCode.SUCCESS,courseBase);
    }

    public QueryResponseResult<CourseInfo> findCourseList(int page, int size, QueryCourseRequest queryCourseRequest)
    {
        //这个分页插件页数从1开始计算
        PageHelper.startPage(page,size);
        Page<CourseInfo> courseList = courseBaseMapper.findCourseList(queryCourseRequest);
        QueryResult<CourseInfo> result = new QueryResult<>();
        result.setList(courseList.getResult());
        result.setTotal(courseList.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS,result);
    }

    public TeachPlanNodeResponse findList(String courseId)
    {
        TeachPlanNode node = teachPlanMapper.findList(courseId);
        return new TeachPlanNodeResponse(CommonCode.SUCCESS,node);
    }

    //todo 了解该注解,用这个注解还可以解决多线程同步问题
    @Transactional
    public TeachPlanResponse addTeachPlan(TeachPlan teachPlan)
    {
        if (teachPlan == null
                || StringUtils.isEmpty(teachPlan.getCourseId())
                || StringUtils.isEmpty(teachPlan.getPName()))
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        //取出课程Id
        String courseId = teachPlan.getCourseId();
        //取出父节点Id
        String parentId = teachPlan.getParentId();
        if (StringUtils.isEmpty(parentId))
            parentId = this.getRootTeachPlan(courseId);
        if (StringUtils.isEmpty(parentId))
            ExceptionCast.cast(CourseCode.COURSE_ROOT_PLAN_IS_NULL);
        Optional<TeachPlan> optional = teachPlanRepository.findById(parentId);
        if (!optional.isPresent())
            ExceptionCast.cast(CourseCode.COURSE_ROOT_PLAN_IS_NULL);
        TeachPlan parentPlan = optional.get();
        String parentLevel = parentPlan.getLevel();
        teachPlan.setParentId(parentId);
        if (parentLevel.equals("1"))
            teachPlan.setLevel("2");
        else
            teachPlan.setLevel("3");
        teachPlan.setStatus("0");
        teachPlan = teachPlanRepository.save(teachPlan);
        return new TeachPlanResponse(CommonCode.SUCCESS,teachPlan);
    }

    private String getRootTeachPlan(String courseId)
    {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (!optional.isPresent())
            return null;
        CourseBase courseBase = optional.get();
        List<TeachPlan> teachPlans = teachPlanRepository.findByCourseIdAndParentId(courseId, "0");
        if (teachPlans == null || teachPlans.size() <= 0)
        {
            TeachPlan teachPlan = new TeachPlan();
            teachPlan.setCourseId(courseId);
            teachPlan.setLevel("1");
            teachPlan.setParentId("0");
            teachPlan.setStatus("0");
            teachPlan.setPName(courseBase.getName());
            teachPlan = teachPlanRepository.save(teachPlan);
            return teachPlan.getId();
        }
        return teachPlans.get(0).getId();
    }

    public CourseResponse findById(String courseId)
    {
        Optional<CourseBase> optional = courseBaseRepository.findById(courseId);
        if (!optional.isPresent())
            ExceptionCast.cast(CourseCode.COURSE_NOT_EXIST);
        CourseBase courseBase = optional.get();
        return new CourseResponse(CommonCode.SUCCESS,courseBase);
    }

    public CourseResponse update(String id, CourseBase courseBase)
    {
        CourseBase origin = this.findById(id).getCourseBase();
        if (StringUtils.isNotEmpty(courseBase.getName()))
            origin.setName(courseBase.getName());
        if (StringUtils.isNotEmpty(courseBase.getUsers()))
            origin.setUsers(courseBase.getUsers());
        if (StringUtils.isNotEmpty(courseBase.getLevel()))
            origin.setLevel(courseBase.getLevel());
        if (StringUtils.isNotEmpty(courseBase.getStudyModel()))
            origin.setStudyModel(courseBase.getStudyModel());
        if (StringUtils.isNotEmpty(courseBase.getMt()))
            origin.setMt(courseBase.getMt());
        if (StringUtils.isNotEmpty(courseBase.getSt()))
            origin.setSt(courseBase.getSt());
        if (StringUtils.isNotEmpty(courseBase.getDescription()))
            origin.setDescription(courseBase.getDescription());
        origin = courseBaseRepository.save(origin);
        return new CourseResponse(CommonCode.SUCCESS,origin);
    }
}
