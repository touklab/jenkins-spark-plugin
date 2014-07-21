package pl.touk.jenkins.spark;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.Result;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Notifier;
import hudson.tasks.Publisher;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

public class SparkNotifier extends Notifier {

    @DataBoundConstructor
    public SparkNotifier() { }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        SparkApi api = SparkApiFactory.getInstance(getDescriptor().accessToken, getDescriptor().deviceId);
        if (build.getResult() == Result.FAILURE) {
            api.notify(getDescriptor().function);
        }
        return true;
    }

    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl) super.getDescriptor();
    }

    @Extension
    public static class DescriptorImpl extends BuildStepDescriptor<Publisher> {

        private String accessToken;
        private String deviceId;
        private String function;

        public DescriptorImpl() {
            load();
        }

        public String getAccessToken() {
            return accessToken;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public String getFunction() {
            return function;
        }

        public boolean isApplicable(Class<? extends AbstractProject> aClass) {
            return true;
        }

        public String getDisplayName() {
            return "Spark notifier";
        }

        @Override
        public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
            accessToken = formData.getString("accessToken");
            deviceId = formData.getString("deviceId");
            function = formData.getString("function");
            save();
            return super.configure(req,formData);
        }

    }
}

