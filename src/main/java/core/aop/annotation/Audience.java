package core.aop.annotation;import org.aspectj.lang.ProceedingJoinPoint;import org.aspectj.lang.annotation.*;import org.springframework.aop.PointcutAdvisor;/** * Created with IntelliJ IDEA. * User: jicui * Date: 12/29/13 * Time: 8:38 PM * To change this template use File | Settings | File Templates. */@Aspectpublic class Audience {    @Pointcut("execution(* core.Performer.perform(..))*")    public void performer() {    }    @Before("performer()")    public void takeSeats(){        System.out.println("The audience is taking their seats");    }    @Before("performer()")    public void turnOffCellPhones(){        System.out.println("the audience is turnning off their cell phones");    }    @AfterReturning("performer()")    public void applaud(){        System.out.println("the audience is applauding for the best performance");    }    @AfterThrowing("performer()")    public void demandRefund(){        System.out.println("Woo we want our money back");    }    /**     * define the around advise,so that we can get the context runtime env,and doing the advice     *     * @param proceedingJoinPoint     */    @Around("performer()")    public void watchPerformance(ProceedingJoinPoint proceedingJoinPoint){        System.out.println("starting around advice");        long start=System.currentTimeMillis();        try {            proceedingJoinPoint.proceed();        } catch (Throwable throwable) {            throwable.printStackTrace();        }        long end=System.currentTimeMillis();        System.out.println("Time elapsed in millseconds"+(end-start));    }}