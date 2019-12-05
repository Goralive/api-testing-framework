node {

        stage("checkout repo") {
            git branch: 'master',
            credentialsId: '615f10ab-dfbe-40d3-8724-0fe7ba578e95',
            url: 'https://github.com/Goralive/api-testing-framework.git'
        }

        stage("build"){
            sh "./gradlew clean api-test:assemble"
        }

        stage("run api test"){
            sh "./gradlew api-test:test"
        }
}