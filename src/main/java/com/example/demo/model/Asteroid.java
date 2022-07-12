package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public final class Asteroid {

    @JsonProperty("neo_reference_id")
    private String neoReferenceid;
    private String name;
    @JsonProperty("nasa_jpl_url")
    private String nasaJplUrl;
    @JsonProperty("absolute_magnitude_h")
    private double absoluteMagnitudeH;
    @JsonProperty("estimated_diameter")
    private EstimatedDiameter estimatedDiameter;
    @JsonProperty("is_potentially_hazardous_asteroid")
    private boolean potentiallyHazardousAsteroid;
    @JsonProperty("close_approach_data")
    private List<CloseApproach> closeApproachData;
    @JsonProperty("orbital_data")
    private OrbitalData orbitalData;

    @Getter
    @Setter
    public static final class MissDistance {

        private String astronomical;
        private String lunar;
        private String kilometers;
        private String miles;
    }
    @Getter
    @Setter
    public static final class CloseApproach {

        @JsonProperty("close_approach_date")
        private String closeApproachDate;
        @JsonProperty("epoch_date_close_approach")
        private long epochDateCloseApproach;
        @JsonProperty("relative_velocity")
        private RelativeVelocity relativeVelocity;
        @JsonProperty("miss_distance")
        public MissDistance missDistance;
        @JsonProperty("orbiting_body")
        private String orbitingBody;
    }
    @Getter
    @Setter
    public static final class EstimatedDiameter {

        public EstimatedDiameterUnit kilometers;
        private EstimatedDiameterUnit meters;
        private EstimatedDiameterUnit miles;
        private EstimatedDiameterUnit feet;

        @Getter
        @Setter
        public static final class EstimatedDiameterUnit {

            @JsonProperty("estimated_diameter_min")
            private String estimatedDiameterMin;
            @JsonProperty("estimated_diameter_max")
            private String estimatedDiameterMax;

            public Double getAverage(){
                Double doubleMin = Double.parseDouble(getEstimatedDiameterMin());
                Double doubleMax = Double.parseDouble(getEstimatedDiameterMax());
                return (doubleMin+doubleMax)/2;
            }
        }
    }

    @Getter
    @Setter
    private static final class OrbitalData {

        @JsonProperty("orbit_id")
        private String orbitId;
        @JsonProperty("orbit_determination_date")
        private String orbitDeterminationDate;
        @JsonProperty("orbit_uncertainty")
        private String orbitUncertainty;
        @JsonProperty("minimum_orbit_intersection")
        private String minimumOrbitIntersection;
        @JsonProperty("jupiter_tisserand_invariant")
        private String jupiterTisserandInvariant;
        @JsonProperty("epoch_osculation")
        private String epochOsculation;
        @JsonProperty("eccentricity")
        private String eccentricity;
        @JsonProperty("semi_major_axis")
        private String semiMajorAxis;
        private String inclination;
        @JsonProperty("ascending_node_longitude")
        private String ascendingNodeLongitude;
        @JsonProperty("orbital_period")
        private String orbitalPeriod;
        @JsonProperty("perihelion_distance")
        private String perihelionDistance;
        @JsonProperty("perihelion_argument")
        private String perihelionArgument;
        @JsonProperty("aphelion_distance")
        private String aphelionDistance;
        @JsonProperty("perihelion_time")
        private String perihelionTime;
        @JsonProperty("mean_anomaly")
        private String meanAnomaly;
        @JsonProperty("mean_motion")
        private String meanMotion;
        private String equinox;

    }
    @Getter
    @Setter
    private static final class RelativeVelocity {

        @JsonProperty("kilometers_per_second")
        private String kilometersPerSecond;
        @JsonProperty("kilometers_per_hour")
        private String kilometersPerHour;
        @JsonProperty("miles_per_hour")
        private String milesPerHour;

        public String getKilometersPerSecond() {
            return kilometersPerSecond;
        }

        public String getKilometersPerHour() {
            return kilometersPerHour;
        }

        public String getMilesPerHour() {
            return milesPerHour;
        }
    }
}